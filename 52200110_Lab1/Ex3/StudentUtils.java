import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentUtils {
    
    public static List<Student> generate()
    {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Minh", 29, 8, 7.7, 6.5));
        list.add(new Student("Huong", 26, 7.7, 9, 8.1));
        list.add(new Student("Lan", 31, 9, 9.2, 7.0));
        list.add(new Student("Khoa", 24, 8.5, 9, 7.7));
        list.add(new Student("Duy", 18, 7, 6.9, 6.5));
        list.add(new Student("Tu", 29, 6.5, 7.7, 8.4));
        list.add(new Student("Diem", 22, 8, 8.3, 6.9));
        list.add(new Student("Linh", 21, 5, 6.6, 7.7));
        

        return list;
    }

    public static void print(List<Student> list)
    {
        System.out.println("\n============ BEGIN ============ ");
        list.forEach(System.out::println); // example of Method Reference
        System.out.println("============ END ============ \n");
    }

    /**
     * @TODO
     * Chuyển đổi cách viết sử dụng new Comparator... sang sử dụng Lambda Expression
     */
    public static void sortByName(List<Student> list) {
        Collections.sort(list, (st1, st2) -> st1.name.compareTo(st2.name));
    }

    /**
     * @TODO
     * Chuyển đổi cách viết sử dụng new Comparator... sang sử dụng Lambda Expression
     */
    public static void sortByAvg(List<Student> list)
    {
        Collections.sort(list, (st1, st2) -> (st1.average() > st2.average()) ? 1 : (st1.average() < st2.average())? -1:0);
    }

    /**
     * @TODO
     * Viết chức năng sắp xếp giảm dần theo tuổi sử dụng lambda expression
     * Gọi phương thức này trong main() để sắp xếp và in ra kết quả
     */
    public static void sortByAgeDescending(List<Student> list)
    {
        Collections.sort(list, (st1,st2) -> st2.age-st1.age);
    }

    /**
     * @TODO
     * Sử dụng Stream API và map để tính điểm trung bình của toàn bộ sinh viên trong danh sách
     */
    public static double avg(List<Student> list)
    {
        return list.stream().mapToDouble(st -> st.average()).average().orElse(0.0);
    }

    /**
     * @TODO
     * Sử dụng Stream API và filter, map để lấy danh sách TÊN của các học sinh giỏi
     */
    public static List<String> goodStudentName(List<Student> list)
    {
        return list.stream().filter(st -> st.average()>=8.0).map(st -> st.name).collect(Collectors.toList());  
    } 
}
