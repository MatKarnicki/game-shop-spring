public class Zad5{
        public static void main(String[] args){
        Author author = new Author("Test Test", "Test@Test.Test", Gender.NONBINARY);

        Book book = new Book(author, "Test", 20.0);
        
        
        author.setName("Maciek Ratthew");
        author.setEmail("IamArat@cheese.com");
        author.setGender(Gender.MALE);
        book.setPrice(50);
        book.setQty(100);
        String actual = author.getEmail() + " " + author.getName() + " " + author.getGender();
        String expected = "IamArat@cheese.com Maciek Ratthew MALE";
        System.out.println(expected.equals(actual)); 
    }
}