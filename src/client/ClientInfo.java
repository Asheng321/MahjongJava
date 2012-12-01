package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.Hai;
import system.Kaze;
import system.MajanHai;
import system.Mentu;
import system.Player;
import system.Sutehai;

public class ClientInfo {
	List<Hai> tehai;

	// 　自分->0, 上家->1,,,,
	Map<Player, Integer> sekiMap;
	Map<Integer, List<Hai>> sutehaiMap;
	Map<Integer, List<Mentu>> hurohaiMap;
	Map<Integer, Integer> tehaiSizeMap;
	Map<StateCode, List<List<Integer>>> ableIndexList;
	Map<Integer, Integer> reachPosMap;
	List<List<Integer>> chiableIndexLists;
	List<List<Integer>> ponableIndexLists;
	List<List<Integer>> ankanableIndexList;
	List<List<Integer>> kakanableIndexList;
	List<Integer> reachableIndexList;
	List<Integer> selectedIndexes;
	Hai sutehai;

	List<Hai> doraList;
	int honba;
	Kaze bakaze;

	Hai tsumoHai;
	int currentTurn;
	Map<Kaze, Integer> kaze;

	public ClientInfo(int index) {
		this.currentTurn = (4 - index) % 4;
		this.tehai = Collections.synchronizedList(new ArrayList<Hai>());
		this.sutehaiMap = Collections
				.synchronizedMap(new HashMap<Integer, List<Hai>>());
		this.hurohaiMap = Collections
				.synchronizedMap(new HashMap<Integer, List<Mentu>>());
		this.tehaiSizeMap = Collections
				.synchronizedMap(new HashMap<Integer, Integer>());
		this.tsumoHai = MajanHai.ITI_MAN;
		this.selectedIndexes = new ArrayList<Integer>();
		this.chiableIndexLists = new ArrayList<List<Integer>>();
		this.ponableIndexLists = new ArrayList<List<Integer>>();
		this.ankanableIndexList = new ArrayList<List<Integer>>();
		this.kakanableIndexList = new ArrayList<List<Integer>>();
		this.kaze = Collections.synchronizedMap(new HashMap<Kaze, Integer>());
		reachPosMap = Collections
				.synchronizedMap(new HashMap<Integer, Integer>());

		reachPosMap.put(0, null);
		reachPosMap.put(1, null);
		reachPosMap.put(2, null);
		reachPosMap.put(3, null);

		ableIndexList = Collections
				.synchronizedMap(new HashMap<StateCode, List<List<Integer>>>());
		ableIndexList.put(StateCode.SELECT_PON_HAI, ponableIndexLists);
		ableIndexList.put(StateCode.SELECT_CHI_HAI, chiableIndexLists);
		ableIndexList.put(StateCode.SELECT_ANKAN_HAI, ankanableIndexList);
		ableIndexList.put(StateCode.SELECT_KAKAN_HAI, kakanableIndexList);
		List<List<Integer>> tmpList = new ArrayList<List<Integer>>();
		tmpList.add(reachableIndexList);
		ableIndexList.put(StateCode.SELECT_REACH_HAI, tmpList);
		ableIndexList.put(StateCode.SELECT_MINKAN,
				new ArrayList<List<Integer>>());

		this.tehai.add(MajanHai.ITI_MAN);
		this.tehai.add(MajanHai.ITI_MAN);
		this.tehai.add(MajanHai.ITI_MAN);
		this.tehai.add(MajanHai.NI_MAN);
		this.tehai.add(MajanHai.SAN_MAN);
		this.tehai.add(MajanHai.YO_MAN);
		this.tehai.add(MajanHai.GO_MAN);
		this.tehai.add(MajanHai.ROKU_MAN);
		this.tehai.add(MajanHai.NANA_MAN);
		this.tehai.add(MajanHai.HATI_MAN);
		this.tehai.add(MajanHai.KYU_MAN);
		this.tehai.add(MajanHai.KYU_MAN);
		this.tehai.add(MajanHai.HAKU);
		for (int i = 0; i < 4; i++)
			this.hurohaiMap.put(i, new ArrayList<Mentu>());

		this.reachableIndexList = new ArrayList<Integer>();
		this.tehaiSizeMap.put(0, 13);
		this.tehaiSizeMap.put(1, 13);
		this.tehaiSizeMap.put(2, 13);
		this.tehaiSizeMap.put(3, 13);
		for (int i = 0; i < 4; i++)
			this.sutehaiMap.put(i, new ArrayList<Hai>());

		this.kaze.put(Kaze.TON, (-index + 4) % 4);
		this.kaze.put(Kaze.NAN, (-index + 5) % 4);
		this.kaze.put(Kaze.SYA, (-index + 6) % 4);
		this.kaze.put(Kaze.PE, (-index + 7) % 4);

		// this.kaze.put(Kaze.TON,0);
		// this.kaze.put(Kaze.NAN,1);
		// this.kaze.put(Kaze.SYA,2);
		// this.kaze.put(Kaze.PE,3);

	}
}
