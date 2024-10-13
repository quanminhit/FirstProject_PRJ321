/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.util;

/**
 *
 * @author ADMIN
 */
public class ApplicationConstants {

    //1 feature
    public class DispatchFeature {

        public static final String STARTUP_CONTROLLER = "startUpController";
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String SHOPPING_CONTROLLER = "shoppingController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_ITEM_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createNewAccountController";
        public static final String LOGOUT_CONTROLLER = "logoutController";
        public static final String CHECKOUT_CONTROLLER = "checkoutController";
        public static final String TEST_SHOPPING = "testShoppingController";
        public static final String TEST_ADD_TO_CART_CONTROLLER = "testAddToCartController";
        public static final String TEST_VIEWCART_PAGE = "testViewCart";
        public static final String TEST_REMOVE_ITEM_CONTROLLER = "testRemoveItemController";
    }

    public class StartUpFeature {
        public static final String SEARCH_PAGE = "homePage";
        public static final String LOGIN_PAGE = "";
    }

    public class LoginFeature {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "homePage";
    }

    public class SearchFeature {

        public static final String SEARCH_PAGE = "homePage";
        public static final String SEARCH_STATIC_PAGE = "searchPage";
    }

    public class DeleteFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class UpdateFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class AddItemToCartFeature {

        public static final String ERROR_PAGE = "errorPage";
    }
    
      public class TestAddToCartFeature {
        public static final String ERROR_PAGE = "errorPage";
        public static final String TEST_SHOPPING = "testShoppingController";
    }

    public class ShoppingFeature {
        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String TEST_VIEWCART_PAGE = "testViewCart";
    }
    public class TestShoppingFeature {
        public static final String TEST_SHOPPING_PAGE = "testShopping";
        public static final String TEST_VIEWCART_PAGE = "testViewCart";
    }
    public class TestRemoveFeature {
        public static final String ERROR_PAGE = "errorPage";
    }
    public class CreateNewAccountFeature {
        public static final String LOGIN_PAGE = "";
        public static final String CREATEACCOUNT_PAGE = "createPage";
    }

    public class CheckOutFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String VIEW_CART_PAGE = "viewCartPage";
    }

    public class LogOutFeature {
        public static final String ERROR_PAGE = "errorPage";
        public static final String LOGIN_PAGE = "";
    }
}
