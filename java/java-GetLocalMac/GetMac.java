import java.net.*;
import java.util.*;

public class GetMac {
	public static void main(String[] args)
	{
		Enumeration<NetworkInterface> nifs;
		try {
			nifs = NetworkInterface.getNetworkInterfaces();
			while(nifs.hasMoreElements())
			{
				NetworkInterface nif = nifs.nextElement();
				byte[]	 mac_b = nif.getHardwareAddress();
				if(mac_b == null)
					continue;
				System.out.print(nif.getDisplayName() + ": ");
				for(int i = 0; i < mac_b.length; i++)
				{
					if(i!=0) {
						System.out.print("-");
					}
					int mac_i = mac_b[i] &0xff;
					System.out.print("" + Integer.toHexString(mac_i));
				}
				System.out.print("\n");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
