/**
 * 
 */
package com.me.nubid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.nubid.model.Bid;
import com.me.nubid.model.Product;
import com.me.nubid.model.User;

/**
 * @author Snehal Patel
 */
public class BidDao extends Dao {
    
    private static final Logger log = LoggerFactory.getLogger(BidDao.class);

    public Bid placeBid(Bid b) {
        try {
            begin();
            Bid bid = new Bid();
            bid.setBidId(b.getBidId());
            bid.setBidProdId(b.getBidProdId());
            bid.setBidderId(b.getBidderId());
            bid.setBidPrice(b.getBidPrice());
            bid.setBidDate(b.getBidDate());
            getSession().save(b);
            commit();
            close();
            return bid;
        } catch (Exception e) {
            rollback();
            log.error("Error while inserting new bid into the database");
        }
        return null;
    }
    
    public String bidPresent(String uuid, String prodId) {
        try {
            begin();
            Query query = getSession().createQuery("from Bid where bidderId=:uuid AND bidProdId=:prid");
            query.setParameter("uuid", uuid);
            query.setParameter("prid", prodId);
            Bid bid = (Bid) query.uniqueResult();
            if(bid != null) {
                commit();
                close();
                return bid.getBidId();
            } else {
                rollback();
                log.error("Bid does not exist");
                return null;
            }
        } catch (Exception e) {
            rollback();
            log.error("Error while fetching user's bid from database");
        }
        return null;
    }
    
    public Bid updateBid(String bidId, Bid b) {
        try {
            begin();
            Bid bid = (Bid) getSession().load(Bid.class, bidId);
            if (bid != null) {
                getSession().update(bidId, b);
                commit();
                close();
                return b;
            } else {
                rollback();
                log.error("Bid does not exist");
            }
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            log.error("Error while updating user's bid into the database");
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Double> getBidsPlacedForProduct(String prodId) {
        try {
            begin();
            Map<String,Double> bidMap = new HashMap<String, Double>();
            Query q = getSession().createQuery(
                    "select u.userEmailAddress as user, bl.bidPrice as bidPrice from User u join Bid bl on u.userUuid = bl.bidderId where bl.bidProdId=:id");
            q.setParameter("id", prodId);
            List<Object[]> bids = q.getResultList();
            
            if(bids!=null && !bids.isEmpty()) {
                for(Object[] bid:bids) {
                    bidMap.put((String) bid[0], (Double) bid[1]);
                }
            }
            commit();
            close();
            return bidMap;
        } catch (HibernateException e) {
            rollback();
            log.error("Could not list bids");
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public Boolean deleteBidsOnDeleteProduct(String prodId) {
        try {
            begin();
            Query query = getSession().createQuery("from Bid where bidProdId=:id");
            query.setParameter("id", prodId);
            List<Bid> bidsToDelete = query.list();
            
            for(Bid bid: bidsToDelete) {
                getSession().delete(bid);
            }
            commit();
            close();
            return true;
        } catch (Exception e) {
            rollback();
            log.error("Error while deleting bids on product delete !");
        }
        return false;
    }
}
