package com.bloomscorp.aster.product.dao.repository;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductImage;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AsterExclude
public interface AsterProductSubCategoryMappingJpaRepository<
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<
        CA,
        SCA,
        CO,
        ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>,
        ? extends AsterProductImage<CA, SCA, CO, P>
        >,
    SCAM extends AsterProductSubCategoryMapping<CA, SCA, CO, P>
    > extends JpaRepository<SCAM, Long> {

    List<SCAM> findByProduct(P product);

    List<SCAM> findBySubCategory(SCA subCategory);
}
