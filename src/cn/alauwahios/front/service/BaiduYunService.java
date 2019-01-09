package cn.alauwahios.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.alauwahios.front.dao.BaiduYunDao;
import cn.alauwahios.front.vo.BaiduYunVO;
import cn.alauwahios.front.vo.PageInfo;

@Service("baiduYunService")
public class BaiduYunService {

	@Autowired
	private BaiduYunDao baiduYunDao;

	public boolean saveStar(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.saveStar(id);
	}

	public boolean saveVisits(int id) {
		// TODO 添加防刷机制
		return baiduYunDao.saveVisits(id);
	}
	
	public boolean saveSort(int id) {
		// TODO 添加防刷机制
		return true;
		//return baiduYunDao.saveSort(id);
	}
	
	public boolean cancelSort(int id) {
		// TODO 添加防刷机制
		return true;
		//return baiduYunDao.cancelSort(id);
	}

	public List<BaiduYunVO> listBaiduYun(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduYunDao.listBaiduYun(pageInfo);
	}
	
	public boolean saveBaiduYunMake(BaiduYunVO vo){
		// TODO 添加防刷机制 链接码校验
		// 同一个IP 1分钟一个
		if(baiduYunDao.limitIpAndPort(vo.getAddIp())){
			return true;
		}
		
		String[] ling = {"b","c","d","e","g","h","i","j","k","m","n","o","p","q","r","s"};
		String[] lingb = {"p","q","r"};
		String[] lingc = {"2","3","4","p","O","Q","S","V","X"};
		String[] lingd = {"4","6","9","o","y","B","D","F","f","G","H","l","N","n","r","s","T","W","w","x"};
		String[] linge = {"f","g","l","S","T","U"};
		String[] lingg = {"f","g","h"};
		String[] lingh = {"s","t","u"};
		String[] lingi = {"5","6","7"};
		String[] lingj = {"I","J","K"};
		String[] lingk = {"V","W","X"};
		String[] lingm = {"i","j","k"};
		String[] lingn = {"v","w","x"};
		String[] lingo = {"8","9","A"};
		String[] lingp = {"L","M","N"};
		String[] lingq = {"Y","Z"};
		String[] lingr = {"a"};
		String[] lings = {"m","n"};
		boolean flag = false;
		String str = vo.getPanShortLink();
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
		if(!flag){
			return true;
		}
		return baiduYunDao.saveBaiduYunMake(vo);
	}
	
	public List<BaiduYunVO> listBaiduYunMake(PageInfo pageInfo) {
		// TODO 添加防刷机制
		return baiduYunDao.listBaiduYunMake(pageInfo);
	}
}
