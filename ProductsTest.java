package Lesson5;

import Lesson5.ProductService;
import Lesson5.Product;
import Lesson5.RetrofitUtils;
import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsTest {
    static ProductService productService;
    static Product product = null;
    static Faker faker = new Faker();
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }
    @Test
    void create_deleteProduct() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        Integer id=response.body().getId();
        Response<ResponseBody> response1 = productService.deleteProduct(id).execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
    }
    @Test
    void modifyProduct() throws IOException {
        Response<Product> response = productService.modifyProduct(new Product(1,"Новой продукт",
                123456,"Food")).execute();
        assertThat(response.body().getTitle(), equalTo("Новой продукт"));
    }
    @Test
    void getProductById() throws IOException {
        Response<Product> response = productService.getProductById(1).execute();
        assertThat(response.body().getTitle(),
                anyOf(containsString("Новой продукт"), containsString("Milk")));
    }
    @Test
    void getProducts() throws IOException {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
