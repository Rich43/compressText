import service.StringProcessorService;

public class MainApplication {
    public static void main(String[] args) {
        final var stringProcessorService = new StringProcessorService();
        System.out.println(stringProcessorService.convertDuplicatedStringIntoCompressedString("AAAAANNNMMMMYYYYuuuu\\\\\\\\\\\\\\UUUUaaaarWWLLLLJ888DDDDDDDDD"));
        System.out.println(stringProcessorService.convertCompressedStringIntoDuplicatedString("5A3N4M4Y4u7\\\\4U4ar2W4LJ3\\8"));
    }
}
