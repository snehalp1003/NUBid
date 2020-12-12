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
            return bid;
        } catch (Exception e) {
            rollback();
            log.error("Error while inserting new bid into the database");
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
            return bidMap;
        } catch (HibernateException e) {
            rollback();
            log.error("Could not list bids");
        }
        return null;
    }
}
