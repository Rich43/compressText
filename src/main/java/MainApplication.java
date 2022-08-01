import service.StringProcessorService;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println(new StringProcessorService().convertDuplicatedStringIntoCompressedString("AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD"));
        System.out.println(new StringProcessorService().convertCompressedStringIntoDuplicatedString("5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\8"));
    }
}
