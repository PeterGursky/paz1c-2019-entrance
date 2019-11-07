package passwords;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.codec.Hex;

public class Passwords {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String heslo = "heslo";
		String sol = UUID.randomUUID().toString();

		long start1 = System.nanoTime();
		System.out.println("Soľ: " + sol);
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashArray = md.digest((heslo + sol).getBytes());
		String hashString = new BigInteger(1,hashArray).toString(16);
		System.out.println("Hash: " + hashString + ", dlzka: " + hashString.length());
		String hashString2 = new String(Hex.encode(hashArray));
		System.out.println("Hash-spring: " + hashString2 + ", dlzka: " + hashString2.length());
		System.out.println("Čas: " + (System.nanoTime() - start1) / 1000000.0 + " ms");
		
		long start2 = System.nanoTime();
		String bcsalt = BCrypt.gensalt();
		String bchash = BCrypt.hashpw(heslo, bcsalt);
		System.out.println("Soľ: " + bcsalt);
		System.out.println("Hash: " + bchash + ", dlzka: " + bchash.length());
		System.out.println("Čas: " + (System.nanoTime() - start2) / 1000000.0 + " ms");
		
	}

}
