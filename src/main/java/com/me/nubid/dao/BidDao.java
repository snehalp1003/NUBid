/**
 * 
 */
package com.me.nubid.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.nubid.model.AdminBidView;
import com.me.nubid.model.AdminUserView;
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
            Query query = getSession().createQuery(
                    "from Bid where bidderId=:uuid AND bidProdId=:prid");
            query.setParameter("uuid", uuid);
            query.setParameter("prid", prodId);
            Bid bid = (Bid) query.uniqueResult();
            if (bid != null) {
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
            Map<String, Double> bidMap = new HashMap<String, Double>();
            Query q = getSession().createQuery(
                    "select u.userEmailAddress as user, bl.bidPrice as bidPrice from User u join Bid bl on u.userUuid = bl.bidderId where bl.bidProdId=:id");
            q.setParameter("id", prodId);
            List<Object[]> bids = q.getResultList();

            if (bids != null && !bids.isEmpty()) {
                for (Object[] bid : bids) {
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
            Query query = getSession()
                    .createQuery("from Bid where bidProdId=:id");
            query.setParameter("id", prodId);
            List<Bid> bidsToDelete = query.list();

            for (Bid bid : bidsToDelete) {
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

    @SuppressWarnings("unchecked")
    public List<AdminBidView> viewAllOpenBids() {
        List<AdminBidView> allOpenBids = new ArrayList<AdminBidView>();
        try {
            begin();
            Query query = getSession().createQuery(
                    "select u.userEmailAddress, p.prodName, p.prodCategory, p.prodMinPrice, p.prodStartDate from User u join Product p on u.userUuid=p.prodSellerId where p.prodBuyerId IS NULL");
            List<Object[]> result = query.list();

            if (result != null && !result.isEmpty()) {
                for (Object[] r : result) {
                    AdminBidView abv = new AdminBidView();
                    abv.setSellerEmail((String) r[0]);
                    abv.setProdName((String) r[1]);
                    abv.setProdCategory((String) r[2]);
                    abv.setBidStartPrice((Double) r[3]);
                    abv.setBidStartDate((Date) r[4]);

                    allOpenBids.add(abv);
                }
            }
            commit();
            close();
            return allOpenBids;
        } catch (Exception e) {
            rollback();
            log.error("Error while fetching all open bids !");
        }
        return allOpenBids;
    }

    @SuppressWarnings("unchecked")
    public List<AdminBidView> viewAllClosedBids() {
        List<AdminBidView> allClosedBids = new ArrayList<AdminBidView>();
        try {
            begin();
            Query query = getSession().createQuery(
                    "select s.userEmailAddress as sellerEmail, p.prodName, p.prodCategory, p.prodMinPrice, p.prodStartDate, b.userEmailAddress as buyerEmail, p.prodFinalPrice, p.prodEndDate from Product p join User s on s.userUuid=p.prodSellerId join User b on b.userUuid=p.prodBuyerId");
            List<Object[]> result = query.list();

            if (result != null && !result.isEmpty()) {
                for (Object[] r : result) {
                    AdminBidView abv = new AdminBidView();
                    abv.setSellerEmail((String) r[0]);
                    abv.setProdName((String) r[1]);
                    abv.setProdCategory((String) r[2]);
                    abv.setBidStartPrice((Double) r[3]);
                    abv.setBidStartDate((Date) r[4]);
                    abv.setBuyerEmail((String) r[5]);
                    abv.setBidFinalPrice((Double) r[6]);
                    abv.setBidEndDate((Date) r[7]);

                    allClosedBids.add(abv);
                }
            }
            commit();
            close();
            return allClosedBids;
        } catch (Exception e) {
            rollback();
            log.error("Error while fetching all clsoed bids !");
        }
        return allClosedBids;
    }

    public List<AdminBidView> searchOpenBids(String category) {
        List<AdminBidView> searchOpenBids = new ArrayList<AdminBidView>();
        try {
            begin();
            Query query = getSession().createQuery(
                    "select u.userEmailAddress, p.prodName, p.prodCategory, p.prodMinPrice, p.prodStartDate from User u join Product p on u.userUuid=p.prodSellerId where p.prodBuyerId IS NULL AND p.prodCategory=:category");
            query.setParameter("category", category);

            List<Object[]> result = query.list();

            if (result != null && !result.isEmpty()) {
                for (Object[] r : result) {
                    AdminBidView abv = new AdminBidView();
                    abv.setSellerEmail((String) r[0]);
                    abv.setProdName((String) r[1]);
                    abv.setProdCategory((String) r[2]);
                    abv.setBidStartPrice((Double) r[3]);
                    abv.setBidStartDate((Date) r[4]);

                    searchOpenBids.add(abv);
                }
            }
            commit();
            close();
            return searchOpenBids;
        } catch (Exception e) {
            rollback();
            log.error("Error while searching for open bids !");
        }
        return searchOpenBids;
    }

    public List<AdminBidView> searchClosedBids(String category) {
        List<AdminBidView> searchClosedBids = new ArrayList<AdminBidView>();
        try {
            begin();
            Query query = getSession().createQuery(
                    "select s.userEmailAddress as sellerEmail, p.prodName, p.prodCategory, p.prodMinPrice, p.prodStartDate, b.userEmailAddress as buyerEmail, p.prodFinalPrice, p.prodEndDate from Product p join User s on s.userUuid=p.prodSellerId join User b on b.userUuid=p.prodBuyerId where p.prodCategory=:category");
            query.setParameter("category", category);

            List<Object[]> result = query.list();

            if (result != null && !result.isEmpty()) {
                for (Object[] r : result) {
                    AdminBidView abv = new AdminBidView();
                    abv.setSellerEmail((String) r[0]);
                    abv.setProdName((String) r[1]);
                    abv.setProdCategory((String) r[2]);
                    abv.setBidStartPrice((Double) r[3]);
                    abv.setBidStartDate((Date) r[4]);
                    abv.setBuyerEmail((String) r[5]);
                    abv.setBidFinalPrice((Double) r[6]);
                    abv.setBidEndDate((Date) r[7]);

                    searchClosedBids.add(abv);
                }
            }
            commit();
            close();
            return searchClosedBids;
        } catch (Exception e) {
            rollback();
            log.error("Error while searching for closed bids !");
        }
        return searchClosedBids;
    }
}
