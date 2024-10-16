package com.demoApp.DAO;

import com.demoApp.DTO.Product;
import com.demoApp.Repository.Repository;
import com.demoApp.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class ProductDAO implements Repository<Product,Long> {
    Session session = HibernateUtils.getSessionFactory().openSession();
    @Override
    public Long add(Product item) {
        session.beginTransaction();
        Long id = (Long) session.save(item);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public Product get(Long id) {
        return session.find(Product.class,id);
    }

    @Override
    public List<Product> getAll() {
        return session.createQuery("FROM Product", Product.class).list();
    }

    @Override
    public boolean removeById(Long id) {
        Product product = get(id);
        if(product!=null){
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Product item) {
        List<Product> products = getAll();
        for(Product product : products){
            if(product.getName().equalsIgnoreCase(item.getName())
                && product.getPrice() == item.getPrice()
            ){
                session.beginTransaction();
                session.delete(product);
                session.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Product item,Long id) {
        Product product = get(id);
        if(product!=null){
            session.beginTransaction();
            product.setName(item.getName());
            product.setPrice(item.getPrice());
            session.update(product);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }
}
