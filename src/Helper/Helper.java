package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPanelChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");

	}
	
	public static void showMsg(String str) {
		
		String msg;
		optionPanelChangeButtonText();
		switch(str) {
		case "fill":
			msg="L�tfen t�m alanlar� doldurunuz.";
			break;
		case "success":
			msg="��lem ba�ar�l�";
			break;
			default:
			msg=str;
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
		
	}
	public static boolean confirm(String str) {
		String msg;
		optionPanelChangeButtonText();
		
		switch(str) {
		case "sure":
			msg="Bu i�lemi ger�ekle�tirmek istiyor musun ?";
			break;
			default:
				msg=str;
		}
		int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
		
		if(res==0) {
			return true;
		}else {
			return false;
		}
		
	}

}
