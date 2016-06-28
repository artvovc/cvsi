package com.winify.cvsi.server.controller;

import com.winify.cvsi.core.dto.CvsiResponse;
import com.winify.cvsi.core.dto.ProductDto;
import com.winify.cvsi.core.dto.ProductListDto;
import com.winify.cvsi.core.dto.builder.ProductBuilder;
import com.winify.cvsi.core.enums.ErrorEnum;
import com.winify.cvsi.db.model.Product;
import com.winify.cvsi.server.facade.ProductFacade;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Artemie on 28.06.2016.
 */
@Controller
@Api(description = "returneaza ")
@RequestMapping(name = "product controller", path = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    final static Logger log = Logger.getLogger(ProductController.class);

    @GetMapping(path = "/buy")
    private HttpEntity<ProductListDto> getAllBuyProduct(){

        int n = 20;

        List<ProductBuilder> productDtoList = new ArrayList<ProductBuilder>();
        for(int i =0; i<=n ; ++i) {
            Product product = new Product();
            product.setId(new Long(i));
            product.setTitle("title_"+i);
            product.setDescription("Lorem ipsum dolor sit amet, at sit dictas apeirian theophrastus, ut mea atomorum honestatis dissentiunt, sit ea ullum bonorum ullamcorper. At nam nisl rebum necessitatibus, ex nibh oportere mei. Vim cu error legere atomorum. Pri persecuti intellegat te.\n" +
                    "\n" +
                    "Eam esse sale forensibus eu, cum choro consetetur in. Et saepe eripuit iudicabit est. Vidisse feugait posidonium cu qui. Sed utamur accusamus ne, ea sit soluta impetus offendit.\n" +
                    "\n" +
                    "Et natum inani mea. Vix option vulputate ea, eum in esse voluptua placerat. Posse lobortis an mei. Quodsi mandamus assentior te cum, ei tritani saperet vix. Eros tota delenit qui an. Et cum eros affert homero.\n" +
                    "\n" +
                    "No ius epicuri commune urbanitas, vide veritus tincidunt ad his. Iudico libris patrioque cum ea. Eu sapientem explicari disputationi mel. Utinam latine ei mel, cu graeco iriure scripserit pri. Novum zril tation ius ut, vel natum quodsi denique cu.\n" +
                    "\n" +
                    "In eligendi comprehensam eum, at ius agam vitae. Nam et quas habemus dissentias. In feugait pertinacia mea, an periculis dissentias eam, legimus civibus sit ei. Ei quem idque facer eos, odio affert periculis an mei, vivendum consulatu vulputate est in.");
            product.setCategory("buy");
            product.setPostedDate(new Date());
            product.setPrice(new BigDecimal(2123124.3341));
            product.setItemForChange("shilo_"+i);
            product.setItemNeeded("milo_"+i);
            product.setLimitDate(new Date());

            productDtoList.add(new ProductBuilder(product));
        }

        ProductListDto productListDto = new ProductListDto(new CvsiResponse(ErrorEnum.UNKNOWN_ERROR,"OK"),productDtoList);
        return new ResponseEntity(productListDto, HttpStatus.OK);
    }
//    @GetMapping(path = "/sell")
//    @GetMapping(path = "/borrow")

}
