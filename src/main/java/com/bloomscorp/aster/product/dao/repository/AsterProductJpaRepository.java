package com.bloomscorp.aster.product.dao.repository;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsterProductJpaRepository<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory<CA>,
        CO extends AsterProductCollection,
        P extends AsterProduct<CA, SCA, CO>
        > extends JpaRepository<P, Long> {

    P findProductBySlug(String slug);
}
