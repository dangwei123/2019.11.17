public class Book {
    public String name;
    public String author;
    public int price;
    public boolean isBorrowed;
    public Book(String name,String author,int price){
        this.name=name;
        this.author=author;
        this.price=price;
    }
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ((isBorrowed==true) ? "，已经被借出":"，未借出")+
                '}';
    }
}

public class BookList {
    public Book[] books=new Book[10];
    public int size;
    public BookList(){
        books[0]=new Book("三国演义","罗贯中",12);
        books[1]=new Book("水浒传","施耐庵",14);
        books[2]=new Book("西游记","吴承恩",15);
        books[3]=new Book("红楼梦","曹雪芹",17);
        this.size=4;
    }

    public void setBooks(int pos,Book book) {
        books[pos]=book;
    }
    public Book getBooks(int pos) {
        return books[pos];
    }
    public void setSize(int size){
        this.size=size;
    }
    public int getSize(){
        return this.size;
    }
}


public class AddOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要添加的书的名字：");
        String name=sc.nextLine();
        System.out.println("请输入要添加的书的作者：");
        String author=sc.nextLine();
        System.out.println("请输入要添加的书的价格：");
        int price=sc.nextInt();

        Book book=new Book(name,author,price);
        int size=bookList.getSize();
        bookList.setBooks(size,book);
        bookList.setSize(size+1);
    }
}



public class BorrowOperation implements IOperation{
    @Override
    public void work(BookList booklist) {
        System.out.println("请输入你要借阅的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<booklist.getSize();i++){
            if(booklist.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==booklist.getSize()){
            System.out.println("没有此书");
        }else if(booklist.getBooks(i).isBorrowed){
            System.out.println("此书已被借阅");
        }else{
            booklist.getBooks(i).isBorrowed=true;
            System.out.println("借阅成功");
        }
    }
}



public class DelOperation implements IOperation{
    @Override
    public void work(BookList booklist) {
        System.out.println("请输入你要删除的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<booklist.getSize();i++){
            if(booklist.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==booklist.getSize()){
            System.out.println("没有此书");
        }else{
            while(i<booklist.getSize()-1){
                Book book=booklist.getBooks(i+1);
                booklist.setBooks(i,book);
                i++;
            }
            System.out.println("删除成功");
        }
    }
}


public class DisplayOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        for(int i=0;i<bookList.getSize();i++){
            System.out.println(bookList.getBooks(i));
        }
    }
}

public class ExitOperation implements IOperation{
    @Override
    public void work(BookList bookList) {
        System.out.println("程序退出");
        System.exit(0);
    }
}

public class FindOperation implements IOperation{
    @Override
    public void work(BookList booklist) {
        System.out.println("请输入你要查找的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<booklist.getSize();i++){
            if(booklist.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==booklist.getSize()){
            System.out.println("没有此书");
        }else if(booklist.getBooks(i).isBorrowed){
            System.out.println("此书已经被借阅");
        }else{
            System.out.println("找到了");
        }
    }
}


public interface IOperation {
     Scanner sc=new Scanner(System.in);
     void work(BookList bookList);
 }
 
 public class ReturnOperation implements IOperation{
    @Override
    public void work(BookList booklist) {
        System.out.println("请输入你要归还的书籍的名字：");
        String name=sc.nextLine();
        int i=0;
        for(;i<booklist.getSize();i++){
            if(booklist.getBooks(i).name.equals(name)){
                break;
            }
        }
        if(i==booklist.getSize()){
            System.out.println("没有此书");
        }else{
            booklist.getBooks(i).isBorrowed=false;
            System.out.println("归还成功");
        }
    }
}


public  abstract class User {
    public String name;
    public IOperation[] operations;
    public abstract int menu();

    public void doOperation(int choice,BookList booklist){
        operations[choice].work(booklist);
    }
}

public class Admin extends User{
    public Admin(String name){
        this.name=name;
        this.operations=new IOperation[]{
                new ExitOperation(),
                new AddOperation(),
                new DelOperation(),
                new FindOperation(),
                new DisplayOperation(),
        };
    }
    @Override
    public int menu() {
        System.out.println("=======================================");
        System.out.println("Hello " + this.name + ", 欢迎使用图书管理系统!");
        System.out.println("1. 新增图书");
        System.out.println("2. 删除图书");
        System.out.println("3. 查找图书");
        System.out.println("4. 显示所有图书");
        System.out.println("0. 退出系统");
        System.out.println("=======================================");
        System.out.println("请输入您的选择: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

public class NormalUser extends User {
    public NormalUser(String name){
        this.name=name;
        this.operations=new IOperation[]{
                new ExitOperation(),
                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation(),
                new DisplayOperation()
        };
    }
    @Override
    public int menu() {
        System.out.println("=======================================");
        System.out.println("Hello " + this.name + ", 欢迎使用图书管理系统!");
        System.out.println("1. 查找图书");
        System.out.println("2. 借阅图书");
        System.out.println("3. 归还图书");
        System.out.println("4. 显示所有图书");
        System.out.println("0. 退出系统");
        System.out.println("=======================================");
        System.out.println("请输入您的选择: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}


public class Main{
    public static User login(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你的名字：");
        String name=sc.nextLine();
        System.out.println("请输入你的身份（1-->管理者   2-->学生：");
        int choice=sc.nextInt();
        if(choice==1){
            return new Admin(name);
        }else{
            return new NormalUser(name);
        }
    }
    public static void main(String[] args) {
        BookList bookList=new BookList();
        User user=login();
        while(true){
           int choice=user.menu();
           user.doOperation(choice,bookList);
        }
    }
}
