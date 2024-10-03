public class Program{
    public static void main(String[] args) {
        try{
            if(args.length!=3) {
                System.out.println(" Invalid expression");
                return;
            }
            switch (args[1]){
                case "+":
                    System.out.println((Integer.parseInt(args[0]) + Integer.parseInt(args[2])));
                    break;
                case "-":
                    System.out.println( (Integer.parseInt(args[0]) - Integer.parseInt(args[2])));
                    break;
                case "x":
                    System.out.println( (Integer.parseInt(args[0]) * Integer.parseInt(args[2])));
                    break;
                case "/":
                    System.out.println( (Float.parseFloat(args[0]) / Float.parseFloat(args[2])));
                    break;
                case "%":
                    System.out.println( (Integer.parseInt(args[0]) % Integer.parseInt(args[2])));
                    break;
                case "^":
                    System.out.println( (Math.pow(Integer.parseInt(args[0]), Integer.parseInt(args[2]))));
                    break;
                default :
                    System.out.println("Unsupported operator");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}