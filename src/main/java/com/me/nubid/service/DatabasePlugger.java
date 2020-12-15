/**
 * 
 */
package com.me.nubid.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.me.nubid.dao.BidDao;
import com.me.nubid.dao.ProductDao;
import com.me.nubid.dao.UserDao;
import com.me.nubid.model.Bid;
import com.me.nubid.model.Login;
import com.me.nubid.model.Product;
import com.me.nubid.model.User;

/**
 * @author Snehal Patel
 */
@Service
public class DatabasePlugger {

    private static final Logger log = LoggerFactory
            .getLogger(DatabasePlugger.class);

    UserDao userDao = new UserDao();
    ProductDao prodDao = new ProductDao();
    BidDao bidDao = new BidDao();

    public User addNewUser(User user) {
        if (user != null) {
            try {
                User u = userDao.addNewUser(user);
                log.info(
                        "*****************************************ADDED NEW USER !!******************************************");
                return u;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHILE CREATING NEW USER !!******************************************");
                log.error("Exception occurred while creating New User : "
                        + e.getMessage() + " for userId : "
                        + user.getUserUuid());
            }
        }
        return null;
    }

    public boolean updateUser(String uuid, User user) {
        if (uuid != null && !uuid.isEmpty() && user != null) {
            try {
                if (userDao.update(uuid, user)) {
                    log.info(
                            "*****************************************UPDATED USER DETAILS !!******************************************");
                    return true;
                } else {
                    log.error(
                            "*****************************************Error occured when updating User Details !!******************************************");
                    return false;
                }
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHILE UPDATING USER !!******************************************");
                log.error("Exception occurred while Updating User : "
                        + e.getMessage() + " for userId : "
                        + user.getUserUuid());
            }
        }
        return false;
    }

    public User getUserDetails(Login login) {
        if (login != null) {
            try {
                User user = userDao.getUser(login.getUserEmailAddress());
                log.info(
                        "*****************************************FETCHED USER DETAILS !!******************************************");
                return user;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING USER DETAILS !!******************************************");
                log.error("Exception occurred when Fetching User Details: "
                        + e.getMessage() + " for userEmail : "
                        + login.getUserEmailAddress());
            }
        }
        return null;
    }
    
    public User getUserDetails(String email) {
        if(UtilityService.checkStringNotNull(email)) {
            try {
                User user = userDao.getUser(email);
                log.info(
                        "*****************************************FETCHED USER DETAILS !!******************************************");
                return user;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING USER DETAILS !!******************************************");
                log.error("Exception occurred when Fetching User Details: "
                        + e.getMessage() + " for userEmail : "
                        + email);
            }
        }
        return null;
    }

    public Product addNewProduct(Product prod) {
        if (prod != null) {
            try {
                Product p = prodDao.addProduct(prod);
                log.info(
                        "*****************************************ADDED NEW PRODUCT !!******************************************");
                return p;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHILE ADDING NEW PRODUCT !!******************************************");
                log.error("Exception occurred while creating New User : "
                        + e.getMessage() + " for prodId : " + prod.getProdId());
            }
        }
        return null;
    }

    public boolean updateProduct(String prodId, Product prod) {
        if (prodId != null && !prodId.isEmpty() && prod != null) {
            try {
                if (prodDao.updateProduct(prodId, prod)) {
                    log.info(
                            "*****************************************UPDATED PRODUCT DETAILS !!******************************************");
                    return true;
                } else {
                    log.error(
                            "*****************************************Error occured when updating Product Details !!******************************************");
                    return false;
                }
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHILE UPDATING PRODUCT !!******************************************");
                log.error("Exception occurred while Updating Product : "
                        + e.getMessage() + " for prodId : " + prod.getProdId());
            }
        }
        return false;
    }

    public Product getProduct(String prodId) {
        if (UtilityService.checkStringNotNull(prodId)) {
            try {
                Product prod = prodDao.getProduct(prodId);
                log.info(
                        "*****************************************FETCHED PRODUCT DETAILS !!******************************************");
                return prod;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING PRODUCT DETAILS !!******************************************");
                log.error("Exception occurred when Fetching Product Details: "
                        + e.getMessage() + " for prodId : " + prodId);
            }
        }
        return null;
    }

    public Boolean deleteProduct(String prodId) {
        if (UtilityService.checkStringNotNull(prodId)) {
            try {
                if (prodDao.deleteProduct(prodId)
                        && bidDao.deleteBidsOnDeleteProduct(prodId)) {
                    log.info(
                            "*****************************************DELETED PRODUCT SUCCESSFULLY !!******************************************");
                    return true;
                }
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING PRODUCT DETAILS !!******************************************");
                log.error("Exception occurred when Fetching Product Details: "
                        + e.getMessage() + " for prodId : " + prodId);
            }
        }
        return false;
    }

    public List<Product> viewProductsForPurchase(String currentUserUuid) {
        if (UtilityService.checkStringNotNull(currentUserUuid)) {
            try {
                List<Product> prodsForPurchase = prodDao
                        .viewProductsForPurchase(currentUserUuid);
                log.info(
                        "*****************************************FETCHED PRODUCTS FOR PURCHASE !!******************************************");
                return prodsForPurchase;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING PRODUCTS FOR PURACHSING !!******************************************");
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Product> viewProductsForSelling(String currentUserUuid) {
        if (UtilityService.checkStringNotNull(currentUserUuid)) {
            try {
                List<Product> prodsPlacedForBidding = prodDao
                        .viewProductsForSelling(currentUserUuid);
                log.info(
                        "*****************************************FETCHED MY PRODUCTS FOR BIDDING !!******************************************");
                return prodsPlacedForBidding;
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING MY PRODUCTS FOR BIDDING !!******************************************");
                e.printStackTrace();
            }
        }
        return null;
    }

    public String bidPresent(String currentUserUuid, String prodId) {
        if (UtilityService.checkStringNotNull(currentUserUuid)
                && UtilityService.checkStringNotNull(prodId)) {
            try {
                String bidId = bidDao.bidPresent(currentUserUuid, prodId);
                if(UtilityService.checkStringNotNull(bidId)) {
                    log.info(
                            "***************************************** OLD BID FOUND !!******************************************");
                    return bidId;
                } else {
                    log.info(
                            "***************************************** NO BID FOUND !!******************************************");
                    return bidId;
                } 
            } catch(Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN SEARCHING FOR OLD BID !!******************************************");
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bid placeBid(Bid bid, Boolean updateBid) {
        if (bid != null) {
            try {
                if (updateBid) {
                    Bid b = bidDao.updateBid(bid.getBidId(), bid);
                    log.info(
                            "*****************************************UPDATED BID OFFER !!******************************************");
                    return b;
                } else {
                    Bid b = bidDao.placeBid(bid);
                    log.info(
                            "*****************************************BID PLACED !!******************************************");
                    return b;
                }

            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN PLACING BID !!******************************************");
                log.error("Exception occurred while Placing Bid : "
                        + e.getMessage() + " for bidLogId : " + bid.getBidId());
            }
        }
        return null;
    }

    public Map<String, Double> getAllBids(String prodId) {
        Map<String, Double> getAllBidsForProd = new HashMap<String, Double>();
        if (prodId != null && !prodId.isEmpty()) {
            try {
                getAllBidsForProd = bidDao.getBidsPlacedForProduct(prodId);
                log.info(
                        "*****************************************FETCHED BIDS FOR PRODUCT !!******************************************");
            } catch (Exception e) {
                log.error(
                        "*****************************************ERROR OCCURED WHEN FETCHING BIDS FOR PRODUCT !!******************************************");
                log.error("Exception occurred when fetching bids for product : "
                        + e.getMessage() + " for prodId : " + prodId);
            }
        }
        return getAllBidsForProd;
    }
}
