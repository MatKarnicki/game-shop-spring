package zad5;
public class zad5{
        public static void main(String[] args){
        Author author = new Author("Test Test", "Test@Test.Test", Gender.NONBINARY);

        Book book = new Book(author, "Test", 20.0);
        System.out.println(author.getEmail() + " " + author.getName() + " " + author.getGender());
      
        author.setName("Maciek Ratthew");
        author.setEmail("IamArat@cheese.com");
        author.setGender(Gender.MALE);
        book.setPrice(50);
        book.setQty(100);
        System.out.println(book);
    }
}