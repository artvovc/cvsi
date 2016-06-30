package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.CvsiResponse;
import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.ProductListDto;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.db.model.User;
import com.winify.cvsi.server.facade.ProductFacade;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@Api(description = "product services ")
@RequestMapping(name = "product controller",
        path = "/product",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    final static Logger log = Logger.getLogger(ProductController.class);

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/all")
    private HttpEntity<ProductListDto> getAllProduct() {

        User user1 = new User();
        user1.setUsername("a");

        int n = 5;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            product.setPrice(new Long(123));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }



    @GetMapping(path = "/buy")
    private HttpEntity<ProductListDto> getAllBuyProduct() {

        int n = 20;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
    @GetMapping(path = "/buy/count")
    private HttpEntity<ProductListDto> getCountBuyProduct(@RequestParam(value = "count", required = false, defaultValue = "1") Integer count) {

        int n = count;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = count; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune , vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
    @GetMapping(path = "/buy/{prodId}")
    private HttpEntity<ProductDto> getBuyProductById(@PathVariable Integer count) {

            Product product = new Product();
            product.setId(new Long(count));
            product.setTitle("title_" + count);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune , vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());
        return new ResponseEntity(new ProductDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),new ProductBuilder(product)), HttpStatus.OK);
    }
    @GetMapping(path = "/sell")
    private HttpEntity<ProductListDto> getSellProduct() {

        int n = 20;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            product.setPrice(new Long(123));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }

    @GetMapping(path = "/sell/count")
    private HttpEntity<ProductListDto> getCountSellProduct(@RequestParam(value = "count", required = false, defaultValue = "1") Integer count) {

        int n = count;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            product.setPrice(new Long(123));
            //product.setLimitDate(new Date());
            //product.setUpdateDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
    @GetMapping(path = "/sell/{prodId}")
    private HttpEntity<ProductDto> getSellProductById(@PathVariable Integer count) {

        Product product = new Product();
        product.setId(new Long(count));
        product.setTitle("title_" + count);
        product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                "\n" +
                "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                "\n" +
                "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                "\n" +
                "No ius epicuri commune , vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                "\n" +
                "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
        product.setCreatedDate(new Date());
        product.setPrice(new Long(123));
        //product.setLimitDate(new Date());
        //product.setUpdateDate(new Date());
        return new ResponseEntity(new ProductDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),new ProductBuilder(product)), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/borrow")
    private HttpEntity<ProductListDto> getAllBorrowProduct() {

        int n = 20;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            product.setLimitDate(new Date());
            //product.setUpdatedDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
    @GetMapping(path = "/borrow/count")
    private HttpEntity<ProductListDto> getCountBorrowProduct(@RequestParam(value = "count", required = false, defaultValue = "1") Integer count) {

        int n = count;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for (int i = 0; i < n; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_" + i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCreatedDate(new Date());
            //product.setPrice(new BigDecimal(2123124.3341));
            product.setLimitDate(new Date());
            //product.setUpdatedDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR, "OK"), productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
    @GetMapping(path = "/borrow/{prodId}")
    private HttpEntity<ProductDto> getBorrowProductById(@PathVariable Integer count) {

        Product product = new Product();
        product.setId(new Long(count));
        product.setTitle("title_" + count);
        product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                "\n" +
                "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                "\n" +
                "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                "\n" +
                "No ius epicuri commune , vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                "\n" +
                "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
        product.setCreatedDate(new Date());
        //product.setPrice(new BigDecimal(2123124.3341));
        product.setLimitDate(new Date());
        //product.setUpdateDate(new Date());
        return new ResponseEntity(new ProductDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),new ProductBuilder(product)), HttpStatus.OK);
    }
    //    @PostMapping(path = "/")
    @PostMapping(path = "/borrow/add")
    private HttpEntity<CvsiResponse> setBorrowProduct(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "price") Long price,
            @RequestParam(value = "limitdate") String limitDate
        ) {


        Product prod = new Product();
        prod.setTitle(title);
        prod.setDescription(description);
        prod.setPrice(price);
        prod.setCreatedDate(new Date());
        prod.setLimitDate(new Date());
        prod.setUpdatedDate(new Date());

        productFacade.saveProduct(prod);
        return new ResponseEntity(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }
    @PostMapping(path = "/buy/add")
    private HttpEntity<CvsiResponse> setBuyProduct(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "price") BigDecimal price,
            @RequestParam(value = "limitdate") String limitDate) {

        return new ResponseEntity(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }
    @PostMapping(path = "/sell/add")
    private HttpEntity<CvsiResponse> setSellProduct(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "price") BigDecimal price,
            @RequestParam(value = "limitdate") String limitDate) {

        return new ResponseEntity(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"), HttpStatus.OK);
    }

}
