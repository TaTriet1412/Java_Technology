package org.example.DAO;

import org.example.DTO.Manufacture;
import org.hibernate.Session;
import org.example.DTO.Phone;

import java.util.List;

public class PhoneDAO {
    Session session;
    public PhoneDAO(Session session){
        this.session = session;
    }

    public boolean add(Phone p){
        if(p.getPrice()<0){
            System.err.println("Price is more than or equals 0!");
            return false;
        }
        if(p.getQuantity()<0){
            System.err.println("Quantity is more than or equals 0!");
            return false;
        }
        if(hasThisNameInPhone(p.getName())){
            System.err.println("This name of phone is existed!");
            return false;
        }else if(!isExistedManuId(p.getManuId())){
            System.err.println("Don't have this manufacture's id!");
            return false;
        }
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        return true;
    }

    public Phone get(int id){
        return session.find(Phone.class,Integer.toUnsignedLong(id));
    }

    public List<Phone> getAll(){
        return session.createQuery("FROM Phone", Phone.class).list();
    }

    public List<Phone> getAllByManuId(int manuId){
        return session.createQuery("FROM Phone p WHERE p.manuId = " + manuId, Phone.class).list();
    }

    public boolean remove(int id){
        Phone queryPhone = get(id);
        if(queryPhone!=null){
            session.beginTransaction();
            session.delete(queryPhone);
            session.getTransaction().commit();
            return true;
        }
        System.err.println("No phone has this id");
        return false;
    }

    public boolean remove(Phone p){
        if(p.getPrice()<0){
            System.err.println("Price is more than or equals 0!");
            return false;
        }
        if(p.getQuantity()<0){
            System.err.println("Quantity is more than or equals 0!");
            return false;
        }
        List<Phone> phonesList = getAll();
        for(Phone ph : phonesList){
            if(ph.getName().equalsIgnoreCase(p.getName())
                && ph.getQuantity() == p.getQuantity()
                && ph.getCountry().equalsIgnoreCase(p.getCountry())
                && ph.getPrice() == p.getPrice()
                && ph.getColor().equalsIgnoreCase(p.getColor())
                && ph.getManuId().equals(p.getManuId())
            ){
                session.beginTransaction();
                session.delete(ph);
                session.getTransaction().commit();
                return true;
            }
        }
        System.err.println("No phone likes this");
        return false;
    }

    public boolean update(Phone p,int idPhone){
        Phone queryPhone = get(Integer.parseInt(Long.toString(idPhone)));
        if(p.getPrice()<0){
            System.err.println("Price is more than or equals 0!");
            return false;
        }
        if(p.getQuantity()<0){
            System.err.println("Quantity is more than or equals 0!");
            return false;
        }
        if(hasThisNameInPhone(p.getName())){
            System.err.println("This name of phone is existed!");
            return false;
        }else if(!isExistedManuId(p.getManuId())){
            System.err.println("Don't have this manufacture's id!");
            return false;
        }
        if(queryPhone!=null){
            session.beginTransaction();
            queryPhone.setColor(p.getColor());
            queryPhone.setName(p.getName());
            queryPhone.setPrice(p.getPrice());
            queryPhone.setManuId(p.getManuId());
            queryPhone.setQuantity(p.getQuantity());
            queryPhone.setCountry(p.getCountry());
            session.update(queryPhone);
            session.getTransaction().commit();
            return true;
        }
        System.err.println("No phone has this id");
        return false;
    }

    public boolean hasThisNameInPhone(String currName){
        List<String> phonesName = session.createQuery("SELECT name FROM Phone", String.class).list();
        for(String phoneName:phonesName){
            if(currName.equals(phoneName)){
                return true;
            }
        }
        return false;
    }

    public boolean isExistedManuId(Long id){
        Manufacture queryM = session.find(Manufacture.class,id);
        return (queryM != null);
    }

//    Highest selling price
    public Phone highestSellingPricePhone(){
        List<Phone> phonesList = getAll();
        if(phonesList.isEmpty()){
            System.err.println("No phone has been added");
            return null;
        }
        int maxPrice = 0;
        Phone maxPricePhone = new Phone();
        for(Phone p:phonesList){
            if(p.getPrice()>maxPrice){
                maxPrice=p.getPrice();
                maxPricePhone = p;
            }
        }
        return maxPricePhone;
    }

//    Sort by contryName and desc price
    public List<Phone> sortListOfPhone(){
        List<Phone> phonesList = getAll();
        if(phonesList.isEmpty()){
            System.err.println("No phone has been added");
            return null;
        }else{
            return session.createQuery("FROM Phone p ORDER BY p.country ASC,p.price DESC ",Phone.class).list();
        }
    }

//    check phone higher than 50M VND
    public List<Phone> listOfPhoneAbove50M(){
        List<Phone> phonesList = getAll();
        if(phonesList.isEmpty()){
            System.err.println("No phone has been added");
            return null;
        }else{
            return session.createQuery("FROM Phone p WHERE p.price > 50000000 ",Phone.class).list();
        }
    }

//    A method to get the first phone in the list that meets the criteria: has the color 'Pink'
//and costs over 15 million.
    public Phone hasColorPinkAndMoreThan15MPhone(){
        List<Phone> phonesList = getAll();
        if(phonesList.isEmpty()){
            System.err.println("No phone has been added");
        }else{
            for(Phone p:phonesList){
                if(p.getColor().equalsIgnoreCase("Pink") && p.getPrice() > 15000000){
                    return p;
                }
            }
        }
        return null;
    }
}
