package system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum NormalYaku implements Yaku {
	RICHI("立直", 1, false, false, true, false) {
		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(RICHI);
		}

	},
	YAKUHAI_HAKU("役牌　白", 1, true, false, false, true) {
		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.get(0).type() == HaiType.HAKU) {
					return true;
				}
			}
			return false;
		}
	},
	YAKUHAI_HATSU("役牌　撥", 1, true, false, false, true) {
		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.get(0).type() == HaiType.HATU) {
					return true;
				}
			}
			return false;
		}
	},
	YAKUHAI_TYUN("役牌　中", 1, true, false, false, true) {
		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.get(0).type() == HaiType.TYUN) {
					return true;
				}
			}
			return false;
		}
	},
	BAHUHAI("場風牌", 1, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.get(0).type() == HaiType.valueOf(field.getBakaze())) {
					return true;
				}
			}
			return false;
		}
	},
	JIHUHAI("自風牌", 1, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.get(0).type() == HaiType.valueOf(param.getJikaze())) {
					return true;
				}
			}
			return false;
		}
	},
	TANNYAO("断么九", 1, true, false, false, false) {
		@Override
		public boolean check(Param param, Field field) {
			for (Hai hai : param.getHaiList()) {
				if (!hai.type().isTyuntyanhai())
					return false;
			}
			return true;
		}
	},
	PINHU("平和", 1, false, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			if (param.isNaki())
				return false;
			HaiType jantohai = param.getJanto();
			if (jantohai.group3() == HaiGroup3.SANGEN) {
				return false;
			} else if (jantohai.group3() == HaiGroup3.KAZE) {
				Kaze kaze = jantohai.kaze();
				if (kaze == field.getBakaze() || kaze == param.getJikaze())
					return false;
			}

			for (Mentu mentu : param.getMentuList()) {
				if (mentu.type() != Mentu.Type.SYUNTU) {
					return false;
				}
			}
			if (param.getMatiType() == MatiType.RYANMEN)
				return true;
			return false;
		}

	},
	TSUMO("門前清自摸和", 1, false, false, false, false) {
		@Override
		public boolean check(Param param, Field field) {
			if (param.isTsumo() && !param.isNaki())
				return true;
			return false;
		}
	},
	IPPATSU("一発", 1, false, false, true, false) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(IPPATSU);
		}

	},

	IPEKO("一盃口", 1, false, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			if (param.isNaki())
				return false;
			List<Mentu> mlist = param.getMentuList();
			//			ArrayList<String> idList = new ArrayList<String>();
			//			for (Mentu mentu : mlist) {
			//				if (mentu.type() == Mentu.Type.SYUNTU) {
			//					int minId = Functions.min(mentu.get(0).type().id(), mentu
			//							.get(1).type().id(), mentu.get(2).type().id());
			//					String id = String.valueOf(minId);
			//					idList.add(id);
			//				}
			//			}
			//			if (idList.size() < 2)
			//				return false;
			//			for (String id : idList) {
			//				idList.remove(id);
			//				if (idList.contains(id))
			//					return true;
			//			}
			for (Mentu m : mlist) {
				List<Mentu> ml = new ArrayList<Mentu>();
				ml.add(m);
				mlist.retainAll(ml);
				if (mlist.size() >= 2)
					return true;
			}

			return false;
		}

	},
	HOTEI("河底撈魚", 1, true, false, true, false) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(HOTEI);
		}

	},
	HAITEI("海底摸月", 1, true, false, true, false) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(HAITEI);
		}

	},
	RINSYANKAIHO("嶺上開花", 1, true, false, true, true) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(RINSYANKAIHO);
		}

	},
	TYANKAN("搶槓", 1, true, false, true, true) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(TYANKAN);
		}

	},
	DABURURICHI("両立直", 2, false, false, true, false) {

		@Override
		public boolean check(Param param, Field field) {
			return param.getFlagCheckYakuSet().contains(DABURURICHI);
		}

	},
	TOITOI("対々和", 2, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			for (Mentu m : param.getMentuList()) {
				if (m.type() == Mentu.Type.SYUNTU) {
					return false;
				}
			}
			return true;
		}

	},
	SANSYOKUDOJUN("三色同順", 2, true, true, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for (Mentu mentu : mlist) {
				if (mentu.type() == Mentu.Type.SYUNTU) {
					int minId = Functions.min(mentu.get(0).type().id(), mentu.get(1).type().id(),
							mentu.get(2).type().id());
					idList.add(minId);
				}
			}
			if (idList.size() < 3)
				return false;
			for (int i = 0; i < 2; i++) {
				int id = idList.get(i);
				if (idList.contains((id + 10) % 30) && idList.contains((id + 20) % 30))
					return true;
			}
			return false;
		}

	},
	SANSYOKUDOKO("三色同刻", 2, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for (Mentu mentu : mlist) {
				if (mentu.type() != Mentu.Type.SYUNTU) {
					int id = mentu.get(0).type().id();
					idList.add(id);
				}
			}
			if (idList.size() < 3)
				return false;
			for (int i = 0; i < 2; i++) {
				int id = idList.get(i);
				if (idList.contains((id + 10) % 30) && idList.contains((id + 20) % 30))
					return true;
			}
			return false;
		}

	},
	CHITOI("七対子", 2, false, false, false, false) {

		@Override
		public boolean check(Param param, Field field) {
			List<Hai> haiList = param.getHaiList();

			if (haiList.size() != 14) {
				return false;
			}

			// List<HaiType> list =
			// Functions.toHaiTypeListFromHaiCollection(haiList);
			List<HaiType> list = HaiType.toHaiTypeList(haiList);

			// Set<HaiType> haiSet = Functions.getHaiTypeSetFromHaiType(list);
			Set<HaiType> haiSet = HaiType.toHaiTypeSet(haiList);
			for (HaiType haiType : haiSet) {
				if (Functions.sizeOfHaiTypeList(haiType, list) != 2)
					return false;
			}
			return true;
		}

	},
	IKKI("一気通貫", 2, true, true, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for (Mentu mentu : mlist) {
				if (mentu.type() == Mentu.Type.SYUNTU) {
					int minId = Functions.min(mentu.get(0).type().id(), mentu.get(1).type().id(),
							mentu.get(2).type().id());
					idList.add(minId);
				}
			}
			if (idList.size() < 3)
				return false;
			for (int id : idList) {
				if (id % 10 == 1) {
					if (idList.contains(id + 3) && idList.contains(id + 6))
						return true;
				}
			}
			return false;
		}

	},
	TYANTA("混全帯么九", 2, true, true, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			HaiType janto = param.getJanto();
			if (janto.isTyuntyanhai())
				return false;
			List<Mentu> mlist = param.getMentuList();
			for (Mentu mentu : mlist) {
				if (mentu.type() == Mentu.Type.SYUNTU) {
					int minNum = Functions.min(mentu.get(0).type().number(), mentu.get(1).type()
							.number(), mentu.get(2).type().number());
					if (minNum != 1 && minNum != 7)
						return false;
				} else {
					if (mentu.get(0).type().isTyuntyanhai())
						return false;
				}
			}

			return true;
		}

	},
	SANNANKO("三暗刻", 2, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			int anko = 0;
			for (Mentu mentu : mlist) {
				if (mentu.type() != Mentu.Type.SYUNTU && !mentu.isNaki()) {
					anko++;
				}
			}
			if (anko == 3)
				return true;
			return false;
		}
	},
	SYOSANGEN("小三元", 2, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			HaiType janto = param.getJanto();
			List<Mentu> mlist = param.getMentuList();
			if (janto.group3() != HaiGroup3.SANGEN) {
				return false;
			}
			int sangenSize = 0;
			for (Mentu mentu : mlist) {
				if (mentu.get(0).type().group3() == HaiGroup3.SANGEN) {
					sangenSize++;
				}
			}
			if (sangenSize == 2)
				return true;

			return false;
		}

	},
	HONROTO("混老頭", 2, true, false, false, false) {

		@Override
		public boolean check(Param param, Field field) {
			for (Hai hai : param.getHaiList()) {
				HaiType haiType = hai.type();
				if (haiType.isTyuntyanhai())
					return false;
			}
			return true;
		}

	},
	SANKANTSU("三槓子", 2, true, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			int kanSize = 0;
			for (Mentu mentu : mlist) {
				if (mentu.type() == Mentu.Type.KANTU)
					kanSize++;
			}
			if (kanSize == 3)
				return true;
			return false;
		}

	},
	JUNTYAN("純全帯么九", 3, true, true, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			List<Mentu> mlist = param.getMentuList();
			HaiType janto = param.getJanto();
			if (janto.isTyuntyanhai() || janto.group2() == HaiGroup2.TU)
				return false;
			for (Mentu mentu : mlist) {
				if (mentu.type() == Mentu.Type.SYUNTU) {
					int minNum = Functions.min(mentu.get(0).type().number(), mentu.get(1).type()
							.number(), mentu.get(2).type().number());
					if (minNum != 1 && minNum != 7)
						return false;
				} else {
					if (mentu.get(0).type().isTyuntyanhai()
							|| mentu.get(0).type().group2() == HaiGroup2.TU)
						return false;
				}
			}
			return true;
		}
	},
	HONNITSU("混一色", 3, true, true, false, false) {

		@Override
		public boolean check(Param param, Field field) {
			SuType suType = null;
			for (Hai hai : param.getHaiList()) {
				HaiType haiType = hai.type();
				if (haiType.group2() == HaiGroup2.TU)
					continue;
				if (suType == null)
					suType = haiType.suType();
				else if (suType != haiType.suType())
					return false;
			}
			return true;
		}

	},
	RYANPEKO("二盃口", 3, false, false, false, true) {

		@Override
		public boolean check(Param param, Field field) {
			if (param.isNaki())
				return false;
			List<Mentu> mlist = param.getMentuList();
			//			ArrayList<String> idList = new ArrayList<String>();
			//			for (Mentu mentu : mlist) {
			//				if (mentu.type() != Mentu.Type.SYUNTU)
			//					return false;
			//				else {
			//					int minId = Functions.min(mentu.get(0).type().id(), mentu.get(1).type().id(),
			//							mentu.get(2).type().id());
			//					String id = String.valueOf(minId);
			//					idList.add(id);
			//				}
			//			}
			//			for (int i = 0; i < 2; i++) {
			//				String id = idList.get(i);
			//				idList.remove(id);
			//				if (!idList.remove(id))
			//					return false;
			//			}
			for (Mentu m : mlist) {
				if (m.type() != Mentu.Type.SYUNTU)
					return false;
				mlist.remove(m);
				if (!m.contains(m))
					return false;
				mlist.remove(m);
			}
			return true;
		}

	},
	CHINNITSU("清一色", 6, true, false, false, false) {

		@Override
		public boolean check(Param param, Field field) {
			SuType suType = null;
			for (Hai hai : param.getHaiList()) {
				HaiType haiType = hai.type();
				if (haiType.group2() == HaiGroup2.TU)
					return false;
				if (suType == null)
					suType = haiType.suType();
				else if (suType != haiType.suType())
					return false;
			}
			return true;
		}
	},
	;

	private final String notation;
	private final int hansu;
	private final boolean naki;
	private final boolean kuisagari;
	private final boolean flagCheck;
	private final boolean kouseiFlag;

	private NormalYaku(String notation, int hansu, boolean naki, boolean kuisagari, boolean flag,
			boolean kousei) {
		this.notation = notation;
		this.hansu = hansu;
		this.naki = naki;
		this.kuisagari = kuisagari;
		this.flagCheck = flag;
		this.kouseiFlag = kousei;
	}

	@Override
	public boolean is4Mentu1Janto() {
		return kouseiFlag;
	}

	@Override
	public String notation() {
		return notation;
	}

	@Override
	public Type type() {
		return Type.NORMAL;
	}

	@Override
	public boolean isFlagCheck() {
		return flagCheck;
	}

	public int getHansu() {
		return hansu;
	}

	public boolean isNaki() {
		return naki;
	}

	public boolean isKuisagari() {
		return kuisagari;
	}

	@Override
	public String toString() {
		return notation;
	}

}
