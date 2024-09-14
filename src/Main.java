import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите a и b:");
        int a = in.nextInt();
        int b = in.nextInt();

        System.out.println("Введите способ умножения:\n1)Математическое свойство умножения\n2)Сложение\n" +
                "3)Умножение через побитовые операции\n4)Использование свойства логарифмов\n" +
                "5)Использование деления\n6)Использование рекурсии");

        int way = in.nextInt();
        int result = 0;

        switch (way){
            case 1:{
                result = (int) ((Math.pow(a + b, 2) - Math.pow(a,2) - Math.pow(b,2)) / 2); // (a + b)^2 = a^2 + 2ab + b^2
                break;                                                                     // ⇒ a * b = ((a + b)^2 – a^2 – b^2) / 2
            }
            case 2:{
                // суммируем a, b - счетчик
                for (int i = 0; i < Math.abs(b); i++) {
                    result += a;
                }
                // меняем знак если b < 0
                if(b < 0) result = -result;
                break;
            }
            case 3:{
                // абсолютные значения для работы с отрицательными числами
                int absA = Math.abs(a);
                int absB = Math.abs(b);

                while (absB > 0) {
                    if ((absB & 1) != 0) { //проверяем самый правый бит b, если он равен 1, то прибавляем текущее a к результату
                        result += absA;
                    }
                    absA <<= 1; // умножаем на 2
                    absB >>= 1; // делим на 2
                }
                // если одно из чисел отрицательно, то меняем знак
                if ((a < 0 && b > 0) || (a > 0 && b < 0)) result = -result;
                break;
            }
            case 4:{
                result = (int) Math.pow(2,Math.log(Math.abs(a)) / Math.log(2) + Math.log(Math.abs(b)) / Math.log(2)); // log2(a) + log2(b) = log2(a * b)
                // если одно из чисел отрицательно, то меняем знак                                                       ⇒ a * b = 2^(log2(a) + log2(b))
                if ((a < 0 && b > 0) || (a > 0 && b < 0)) result = -result;
                break;
            }
            case 5:{
                result = (int) ((int) (double) a / ( 1 / (double) b)); // a * b = a / (1 / b)
                break;
            }
            case 6:{
                result = multiplyRecursion(a,b);
                break;
            }
            default: System.out.println("Способа с таким номером нет");
        }
        System.out.println("Ответ: " + result);
    }

    public static int multiplyRecursion(int a, int b){
        // если один из множителей равен нулю, то возвращаем 0
        if (b == 0) return 0;

        if (a == 0) return  0;

        // рекурсивно вызываем функцию, каждый раз прибавляя в результату a, b выступает в роли счетчика
        if (b > 0) return (a + multiplyRecursion(a, b - 1));

        return -multiplyRecursion(a, -b); // если b < 0, то сразу меняем знак на противоположный
    }
}