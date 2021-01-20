# NUBid
NUBid is a Spring MVC web application with Hibernate annotations-based mappings for enhanced database interaction with MySQL. An open market platform which allows qualified Northeastern members to sell a product by offering them for bidding – allowing members to bid and sell to the highest bidder. Once an item is placed for sale, the auctioneer will start at a relatively low price to attract bidders. The price increases each time someone makes a new, higher bid until finally, no other bidders are willing to offer more than the most recent bid, and the highest bidder takes the item. An auction is considered complete when the seller accepts the highest bid offered and the buyer pays for the goods or services and takes possession of them.
The framework is completely menu driven and user-friendly to give speedy and exact data. The application empowered users to get best deals on the price of the product. A user can enlist a product for selling and decide the minimum bidding price for it. He can also simultaneously act as a buyer and search for available products to purchase and place bids on them. All the CRUD operations were performed for the application.

### Functionality:
Every member on this platform should be registered with their NEU email address (@northeastern.edu) to ensure only valid Northeastern members have access to the site. They should also update their personal information on the portal.
  User Role-
    1. List an item for sale and provide item details, pictures and the base price for selling
    2. View products available for sale and the bids offered by others for purchase of an item
    3. Delete a sale listing
    4. In case of multiple people offering the same bid, seller is given the freedom to choose a buyer as per his convenience
    5. Send an email to buyer’s if his bid is accepted
    6. A bid is closed when an offer is accepted
  Admin Role-
    1. View all the open bids on portal
    2. View all the closed bids on portal
    3. View all the registered members and their contact information on the portal and reach out to them incase of complaints

### Technologies:
  1. Spring MVC, Hibernate, Maven, HTML, JSP
  2. Criteria for selection queries and HQL for save, update and delete
  3. Used Associations like oneToMany, ManyToOne etc
  4. Applied Spring Validators for the validation of data
  5. Added Interceptors, HTTP Session Management techniques and performed Role based authorization to secure web application
  6. Encoded user password using BCrypt hashing encoding
