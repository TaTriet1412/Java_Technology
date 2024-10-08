package org.example.DAO;

import org.example.DTO.Manufacture;
import org.example.DTO.Phone;
import org.hibernate.Session;

import java.util.List;

public class ManufactureDAO {
    Session session;
    public ManufactureDAO(Session session){
        this.session = session;
    }

    public boolean add(Manufacture m){
        if(hasThisNameInManuFacture(m.getName())){
            System.err.println("This name of manufacture is existed!");
            return false;
        }
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        return true;
    }


    public Manufacture get(int id){
        return session.find(Manufacture.class,Integer.toUnsignedLong(id));
    }

    public List<Manufacture> getAll(){
        return session.createQuery("FROM Manufacture", Manufacture.class).list();
    }

    public boolean remove(int id){
        Manufacture queryManufacture = get(id);
        if(queryManufacture==null){
            System.err.println("No manufacture has this id");
            return false;
        }
        if(phoneOfThisManu(id).isEmpty()){
            session.beginTransaction();
            session.delete(queryManufacture);
            session.getTransaction().commit();
            return true;
        }
        System.err.println("This manufacture has phones.So you can't remove it!");
        return false;
    }

    public boolean remove(Manufacture m){
        List<Manufacture> manufacturesList = getAll();
        for(Manufacture ma : manufacturesList){
            if(ma.getName().equalsIgnoreCase(m.getName())
                && ma.getLocation().equalsIgnoreCase(m.getLocation())
                && ma.getEmployee() == m.getEmployee()
            ){
                session.beginTransaction();
                session.delete(ma);
                session.getTransaction().commit();
                return true;
            }
        }
        System.err.println("No manufacture likes this");
        return false;
    }

    public boolean update(Manufacture m,int manuID){
        Manufacture queryManufacture = get(manuID);
        if(m.getEmployee()<0){
            System.err.println("Employee is more than or equals 0!");
            return false;
        }
        if(hasThisNameInManuFacture(m.getName())) {
            System.err.println("This name of manufacture is existed!");
            return false;
        }
        if(queryManufacture!=null){
            session.beginTransaction();
            queryManufacture.setLocation(m.getLocation());
            queryManufacture.setName(m.getName());
            queryManufacture.setEmployee(m.getEmployee());
            session.update(queryManufacture);
            session.getTransaction().commit();
            return true;
        }
        System.err.println("No manufacture has this id");
        return false;
    }



    public List<Phone> phoneOfThisManu(int id){
        if(get(id)!=null){
            return session.createQuery("FROM Phone p WHERE p.manuId = " + id, Phone.class).list();
        }else{
            System.err.println("No manufacture has this id");
            return null;
        }
    }

    public boolean hasThisNameInManuFacture(String currName){
        List<String> manusName = session.createQuery("SELECT name FROM Manufacture", String.class).list();
        for(String manuName:manusName){
            if(currName.equals(manuName)){
                return true;
            }
        }
        return false;
    }



//    A method to check whether all manufacturers have more than 100 employees.
    public boolean isMoreThan100Employee(){
        return sumOfEmployee()>100;
    }

//    A method that returns the sum of all employees of the manufactures.
    public Long sumOfEmployee(){
        return (session.createQuery("SELECT SUM(employee) FROM Manufacture",Long.class).getSingleResult());
    }

//    A method that returns the last manufacturer in the list of manufacturers that meet
//the criteria: based in the US. If there is no producer that meets the above criteria,
//throw an InvalidOperationException.
    public Manufacture theLastUsManu(){
        List<Manufacture> manuList = getAll();
        if(manuList.isEmpty()){
            System.err.println("No manufacture has been added");
            return null;
        }else{
            Manufacture resultManu = null;
            for(Manufacture ma:manuList){
                if(ma.getLocation().equalsIgnoreCase("US")){
                    resultManu = ma;
                }
            }
            return resultManu;
        }
    }

}
