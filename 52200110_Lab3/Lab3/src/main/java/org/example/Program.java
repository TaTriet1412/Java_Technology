package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.DTO.Manufacture;
import org.example.DTO.Phone;
import org.example.MyException.InvalidOperationException.java.InvalidOperationException;
import org.hibernate.Session;
import org.example.utils.HibernateUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Program
{
    public static void main( String[] args )
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            PhoneDAO phoneDAO = new PhoneDAO(session);
            ManufactureDAO manufactureDAO = new ManufactureDAO(session);
            System.out.println("Welcome to management software application!");
            while(true){
                int optionNum = -1;
                Scanner sc = new Scanner(System.in);
                do {

                    interactionInfo();
                    System.out.print("Enter your option: ");
                    while(!sc.hasNextInt()){
                        System.out.print("Please enter an integer number option: ");
                        sc.next();
                    }
                    optionNum = sc.nextInt();
                    sc.nextLine();

//                Main Process
                    switch (optionNum){
                        case 0:
                            System.out.println("Bye!");
                            return;
                        case 1:
                            if(phoneDAO.add(enterInfoPhone(sc))) {
                                System.out.println("Added new phone!");
                            }
                            break;
                        case 2:
                            Phone phoneById = phoneDAO.get(enterIdPhone(sc));
                            if(phoneById!=null){
                                System.out.println(phoneById);
                            }else{
                                System.err.println("No phone has this id");
                            }
                            break;
                        case 3:
                            List<Phone> phonesList = phoneDAO.getAll();
                            if(!phonesList.isEmpty()){
                                printAllPhone(phonesList);
                            }else{
                                System.err.println("No phone in inventory");
                            }
                            break;
                        case 4:
                            if(phoneDAO.remove(enterIdPhone(sc))){
                                System.out.println("Remove phone successfully!");
                            }
                            break;
                        case 5:
                            if(phoneDAO.remove(enterInfoPhone(sc))) {
                                System.out.println("Remove phone successfully!");
                            }
                            break;
                        case 6:
                            int idPhoneUpdate = (enterIdPhone(sc));
                            Phone phoneUpdate = enterInfoPhone(sc);
                            if(phoneDAO.update(phoneUpdate,idPhoneUpdate))
                                System.out.println("Update phone successfully!");
                            break;
                        case 7:
                            int manuId = enterIdManufacture(sc);
                            List<Phone> phonesListByManuId = phoneDAO.getAllByManuId(manuId);
                            if(!phonesListByManuId.isEmpty()){
                                printAllPhone(phonesListByManuId);
                            }else{
                                System.err.println("No phone in inventory with this manuId");
                            }
                            break;
                        case 8:
                            Phone highesPricePhone = phoneDAO.highestSellingPricePhone();
                            if(highesPricePhone!=null)
                                System.out.println(highesPricePhone);
                            break;
                        case 9:
                            List<Phone> phonesListSort = phoneDAO.sortListOfPhone();
                            if(!phonesListSort.isEmpty()){
                                printAllPhone(phonesListSort);
                            }else{
                                System.err.println("No phone in inventory");
                            }
                            break;
                        case 10:
                            List<Phone> phoneMore50M = phoneDAO.listOfPhoneAbove50M();
                            if(!phoneMore50M.isEmpty()){
                                printAllPhone(phoneMore50M);
                            }else{
                                System.out.println("No phone more than 50M VND");
                            }
                            break;
                        case 11:
                            Phone phoneRequire = phoneDAO.hasColorPinkAndMoreThan15MPhone();
                            if(phoneRequire!=null){
                                System.out.println(phoneRequire);
                            }else{
                                System.out.println("No phone likes this");
                            }
                            break;
                        case 12:
                            if(manufactureDAO.add(enterInfoManufacture(sc))) {
                                System.out.println("Added new manufacture!");
                            }
                            break;
                        case 13:
                            Manufacture manufactureById = manufactureDAO.get(enterIdManufacture(sc));
                            if(manufactureById!=null){
                                System.out.println(manufactureById);
                            }else{
                                System.err.println("No manufacture has this id");
                            }
                            break;
                        case 14:
                            List<Manufacture> manufacturesList = manufactureDAO.getAll();
                            if(!manufacturesList.isEmpty()){
                                printAllManufacture(manufacturesList);
                            }else{
                                System.err.println("No manufacture in data");
                            }
                            break;
                        case 15:
                            if(manufactureDAO.remove(enterIdManufacture(sc))){
                                System.out.println("Remove manufacture successfully!");
                            }
                            break;
                        case 16:
                            if(manufactureDAO.remove(enterInfoManufacture(sc))) {
                                System.out.println("Remove manufacture successfully!");
                            }
                            break;
                        case 17:
                            int idManufactureUpdate = (enterIdManufacture(sc));
                            Manufacture manufactureUpdate = enterInfoManufacture(sc);
                            if(manufactureDAO.update(manufactureUpdate,idManufactureUpdate))
                                System.out.println("Update manufacture successfully!");
                            break;
                        case 18:
                            if(manufactureDAO.isMoreThan100Employee()){
                                System.out.println("All manufacture have more than 100 employees");
                            }else {
                                System.out.println("All manufacture have not more than 100 employees");
                            }
                            break;
                        case 19:
                            System.out.println("Sum of employees = " + manufactureDAO.sumOfEmployee());
                            break;
                        case 20:
                            Manufacture lastManuUS = manufactureDAO.theLastUsManu();
                            if(lastManuUS!=null){
                                System.out.println(lastManuUS);
                            }else{
                                throw new InvalidOperationException();
                            }
                            break;
                        default:
                            System.err.println("Your option must be >= 0");
                    }
                }while(optionNum<0);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        } catch (InvalidOperationException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void interactionInfo(){
        System.out.println("\n-----------Options------------");
        System.out.println("0. Exit Program");
        System.out.println("-----------Phone Interaction------------");
        System.out.println("1. Add new phone");
        System.out.println("2. See info phone by id");
        System.out.println("3. See info all phone");
        System.out.println("4. Remove phone by Id");
        System.out.println("5. Remove phone by info");
        System.out.println("6. Update phone by info");
        System.out.println("7. See info phone of manufacture by id");
        System.out.println("8. See the phone with highest price");
        System.out.println("9. See all phone with sort");
        System.out.println("10. See all phone with price >50M VND");
        System.out.println("11. Phone has color 'Pink' and  more than 15M VND");
        System.out.println("-----------Manufacture Interaction------------");
        System.out.println("12. Add new manufacture");
        System.out.println("13. See info manufacture by id");
        System.out.println("14. See info all manufacture");
        System.out.println("15. Remove manufacture by Id");
        System.out.println("16. Remove manufacture by info");
        System.out.println("17. Update manufacture by info");
        System.out.println("18. Check all manufacture have more than 100 employees");
        System.out.println("19. Sum of employees all manufacture");
        System.out.println("20. Manufacture based in the US");
    }

    public static Phone enterInfoPhone(Scanner sc){
        String name;
        int price;
        String color;
        String country;
        int quantity;
        long manuID;

        System.out.print("Enter name of phone: ");
        name = sc.nextLine();

        System.out.print("Enter price: ");
        while(!sc.hasNextInt()){
            System.out.println("Please enter integer number for price!");
            sc.next();
        }
        price = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter color: ");
        color = sc.nextLine();

        System.out.print("Enter country: ");
        country = sc.nextLine();

        System.out.print("Enter quantity: ");
        while(!sc.hasNextInt()){
            System.out.print("Please enter integer number for quantity: ");
            sc.next();
        }
        quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter manufacture id: ");
        while(!sc.hasNextInt()){
            System.out.print("Please enter integer number for manufacture id: ");
            sc.next();
        }
        manuID = Long.parseLong(Integer.toString(sc.nextInt()));
        sc.nextLine();
        Phone resultPhone = new Phone();
        resultPhone.setName(name);
        resultPhone.setQuantity(quantity);
        resultPhone.setPrice(price);
        resultPhone.setCountry(country);
        resultPhone.setManuId(manuID);
        resultPhone.setColor(color);
        return resultPhone;
    }

    public static int enterIdManufacture(Scanner sc){
        int resultManuId;
        System.out.print("Enter id of manufacture: ");
        while(!sc.hasNextInt()){
            System.out.print("Please enter integer number for manufacture id: ");
            sc.next();
        }
        resultManuId = sc.nextInt();
        sc.nextLine();
        return resultManuId;
    }

    public static Manufacture enterInfoManufacture(Scanner sc){
        String name;
        int employee;
        String location;

        System.out.print("Enter name of manufacture: ");
        name = sc.nextLine();

        System.out.print("Enter quantity of employee: ");
        while(!sc.hasNextInt()){
            System.out.println("Please enter integer number for employee!");
            sc.next();
        }
        employee = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter location: ");
        location = sc.nextLine();
        Manufacture resultManu = new Manufacture();
        resultManu.setEmployee(employee);
        resultManu.setName(name);
        resultManu.setLocation(location);
        return resultManu;

    }

    public static void printAllPhone(List<Phone> listPhone){
        System.out.println("-------------Phones----------------");
        for(Phone p:listPhone){
            System.out.println(p);
        }
    }

    public static void printAllManufacture(List<Manufacture> listManufacture){
        System.out.println("-------------Manufactures----------------");
        for(Manufacture m:listManufacture){
            System.out.println(m);
        }
    }



    public static int enterIdPhone(Scanner sc){
        int resultPhoneId;
        System.out.print("Enter id of phone: ");
        while(!sc.hasNextInt()){
            System.out.print("Please enter integer number for phone id: ");
            sc.next();
        }
        resultPhoneId = sc.nextInt();
        sc.nextLine();
        return resultPhoneId;
    }
}




//            session.beginTransaction();

//            //new Manufacture
//            Manufacture manufacture = new Manufacture();
//            manufacture.setEmployee(1234562);
//            manufacture.setLocation("Korea");
//            manufacture.setName("Moon Factory");
//
//            Long manuID = (Long)session.save(manufacture);
//            System.out.println(manuID);

//              //query data List Manufacture
//            List<Manufacture> queryManu = session.createQuery("FROM Manufacture", Manufacture.class).list();
//            System.out.println(queryManu.get(0).getName());

////            query a manufacture by id
//            Manufacture queryM = session.find(Manufacture.class,Integer.toUnsignedLong(3));
//            System.out.println(queryM.getName());

////            update Name
//            queryM.setName("Moon1 Factory");
//            session.update(queryM);
//            System.out.println(queryM.getName());

////            delete Manufacture
//            session.delete(queryM);

//            commit changes
//            session.getTransaction().commit();



//            //new Phone
//            Phone p = new Phone();
//            p.setName("Samsung Galaxy A71");
//            p.setCountry("Japan");
//            p.setColor("Đỏ");
//            p.setPrice(300);
//            p.setQuantity(20);
//            Long userId = (Long) session.save(p);
//            System.out.println(userId);
