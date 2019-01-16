package cn.alauwahios.front.util;

/**
 * 百度云链接的构造规则
 * @author sekift
 *
 */
public class BaiduLinkUtil {
	
	public static boolean panLink(String str){
		String[] ling = {"b","c","d","e","g","h","i","j","k","m","n","o","p","q","r","s"};
		
		                //o 0.05% p 1.01% q 4.70% r 0.93%
		String[] lingb = {"o","p","q","r"};
		                //c1 0.06% c2 0.88% c3 4.66% c4 0.94%
		String[] lingc = {"1","2","3","4","N","P","O","Q","R","S","U","V","W","X","Y","Z"};
		                //dE 0.05% dF 0.96% dG 4.78% dH 1.08%
		String[] lingd = {"0","1","2","3","4","5","6","7","8","9","O","o","y","A","a","B","b","C","c","D","d","E","e",
				"F","I","i","f","J","j","K","G","g","H","h","L","l","P","p","M","N","n","R","r","S","s","Q","q",
				"T","t","U","V","v","W","w","X","x","Y","y","Z","z"};
		//eR 0.05% eS 0.80% eT 4.71% eU 1.10%
		String[] linge = {"a","b","c","d","e","f","g","h","i","j","k","l","R","S","T","U"};
                         //0.03% 0.83% 4.64% 1.12%
		String[] lingg = {"e","f","g","h"};
                         //0.04% 0.69% 4.44% 1.03%
		String[] lingh = {"r","s","t","u"};
                         //0.05% 0.62% 4.47% 1.22%
		String[] lingi = {"4","5","6","7"};
                         //0.03% 0.61% 4.64% 1.25%
		String[] lingj = {"H","I","J","K"};
        //0.03% 0.58% 4.61% 1.21%
		String[] lingk = {"U","V","W","X"};
		//0.03% 0.54% 4.46% 1.37%
		String[] lingm = {"h","i","j","k"};
		//0.05% 0.46% 4.78% 1.41%
		String[] lingn = {"u","v","w","x"};
		//0.03% 0.49% 4.55% 1.38%
		String[] lingo = {"7","8","9","A"};
		//0.02% 0.44% 4.64% 1.51%
		String[] lingp = {"K","L","M","N"};
		//0.02% 0.33% 4.55%
		String[] lingq = {"X","Y","Z"};
		//1.63%
		String[] lingr = {"a"};
		//0.02% 0.31% 4.62% 1.69%
		String[] lings = {"k","l","m","n"};
		
		boolean flag = false;
		
		for(String st:ling){
			if(str.startsWith(st)){
				if(st.equals("b")){
					for(String s:lingb){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("c")){
					for(String s:lingc){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("d")){
					for(String s:lingd){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("e")){
					for(String s:linge){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("g")){
					for(String s:lingg){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("h")){
					for(String s:lingh){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("i")){
					for(String s:lingi){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("j")){
					for(String s:lingj){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("k")){
					for(String s:lingk){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("m")){
					for(String s:lingm){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("n")){
					for(String s:lingn){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("o")){
					for(String s:lingo){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("p")){
					for(String s:lingp){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("q")){
					for(String s:lingq){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("r")){
					for(String s:lingr){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}else if(st.equals("s")){
					for(String s:lings){
						if(str.startsWith(st+s)){
						flag = true;
						break;
						}
					}
				}
			}
		}
		return flag;
	}

}
