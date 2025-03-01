import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant Restaurant1 = Mockito.spy(restaurant);
        LocalTime Time_Within_openingTime_And_closingTime = LocalTime.parse("12:30:00");
        Mockito.when(Restaurant1.getCurrentTime()).thenReturn(Time_Within_openingTime_And_closingTime);
        assertEquals(true,Restaurant1.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant Restaurant1 = Mockito.spy(restaurant);
        LocalTime Time_Not_Within_openingTime_And_closingTime = LocalTime.parse("23:30:00");
        Mockito.when(Restaurant1.getCurrentTime()).thenReturn(Time_Not_Within_openingTime_And_closingTime);
        assertEquals(false,Restaurant1.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //-------TESTS FOR ORDER TOTAL PRICE

    @Test
    public void Order_total_price_initially_should_be_zero(){
        assertEquals(0,restaurant.getOrderTotalValue());
    }

    @Test
    public void Order_total_price_should_be_the_respective_item_price_on_adding_the_first_menu_item(){
        restaurant.orderValue("Sweet corn soup");
        assertEquals(119,restaurant.getOrderTotalValue());
    }

    @Test
    public void Order_total_price_should_be_the_total_of_the_first_menu_item_and_second_menu_item_on_adding_the_second_menu_item(){
        restaurant.orderValue("Sweet corn soup");
        restaurant.orderValue("Vegetable lasagne");
        assertEquals(388,restaurant.getOrderTotalValue());
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}