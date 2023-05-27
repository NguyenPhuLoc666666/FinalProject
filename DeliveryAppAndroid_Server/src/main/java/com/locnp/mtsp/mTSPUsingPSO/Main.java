package com.locnp.mtsp.mTSPUsingPSO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int numOfParticles = 50;
		int numOfPositions = 19;
		int numOfShippers = 3;
		double c1 = 2;
		double c2 = 2;
		double wMax = 0.9;
		
		double[][] distances2d = {
				{ 0.0, 0.16206776948516302, 0.5200056073980532, 0.3025286125203258, 0.506875878192164,
						0.5966235100480383, 0.6871889161012471, 0.1621678576922767, 1.1437496543531531,
						0.9375884968140991, 0.7242829789596977, 0.58325236121914, 0.6530171748280855,
						0.9039377828009775, 0.982967428458987, 1.5446890391087509, 1.992004996879986,
						1.4650728165440203, 1.962441494668488, 1.2368813836806458, 1.1389831399462942,
						1.2494762123654162, 1.4320735530109943, 2.857158550607521, 0.31839091450263013 },
				{ 0.16206776948516302, 0.0, 0.6753237869633827, 0.46323664150432137, 0.6652690304067425,
						0.7586178320520817, 0.8356639558579342, 0.15400675188336213, 1.3043183633983146,
						1.0841732067605565, 0.8108246744533113, 0.6385082308891917, 0.6732029177282913,
						0.9680067367115353, 1.0788771065232028, 1.693053994288486, 2.1374241353385464,
						1.5866454454198238, 2.0964194588610634, 1.3575386712089788, 1.0991524727384423,
						1.3012622211979623, 1.5625028978624262, 2.9855667574534803, 0.4022628431313873 },
				{ 0.5200056073980532, 0.6753237869633828, 0.0, 0.2908380182512574, 0.29200951502831823,
						0.2163001281770858, 0.48881492453765296, 0.6558045022065756, 0.7222462637085967,
						0.6894462173671562, 0.8305773935226485, 0.8233854962031284, 0.9571656590501431,
						1.0454614645897953, 0.9982548049041942, 1.028875624360622, 1.479230481279773,
						1.0149188750763558, 1.4715305101992813, 0.7986424546536901, 1.5112193162520122,
						1.385067767413847, 1.2312585764149417, 2.6152740570864283, 0.4080102381169919 },
				{ 0.3025286125203258, 0.46323664150432137, 0.2908380182512574, 0.0, 0.20699660705300466,
						0.2989687328735858, 0.4076174789721509, 0.3973167986895514, 0.8412683511694884,
						0.656752362117806, 0.5977262539081614, 0.5483079994125415, 0.6736833056494976,
						0.808187697754327, 0.8133951415093356, 1.3052891760372345, 1.757727948009129,
						1.3039184378841802, 1.7605249717251799, 1.0847130929493947, 1.2234396987875198,
						1.1580290233387596, 1.176942503030015, 2.597776751264347, 0.3987289162562041 },
				{ 0.506875878192164, 0.6652690304067425, 0.29200951502831823, 0.20699660705300466, 0.0,
						0.14650361260321396, 0.21952099628581612, 0.5808829959825337, 0.6413597694791817,
						0.45915647811452553, 0.5472486829392279, 0.5762400071317826, 0.7220180179207759,
						0.7611093461156536, 0.7065331175870889, 1.1937655187580505, 1.6451559721232156,
						1.266892168882502, 1.678764685259441, 1.0626099837178762, 1.28002332784617, 1.095699614382259,
						0.9920729720881399, 2.405325653464592, 0.5730981973307111 },
				{ 0.5966235100480383, 0.7586178320520817, 0.2163001281770858, 0.2989687328735858, 0.14650361260321396,
						0.0, 0.28398041345977654, 0.6949088796298145, 0.5548121131340912, 0.47317624848109996,
						0.6798970403856874, 0.722042282604822, 0.8683802911814711, 0.8909264442687987,
						0.8109883960808932, 1.0476868251565792, 1.4994067238790827, 1.1290900663342653,
						1.5327254786304514, 0.9304635612547123, 1.4262000563381405, 1.2161180266948812,
						1.0151172804223885, 2.4048811954008147, 0.5863087646960253 },
				{ 0.687188916101247, 0.8356639558579342, 0.48881492453765296, 0.4076174789721509, 0.2195209962858161,
						0.28398041345977654, 0.0, 0.7252387481055351, 0.5332530206118582, 0.2508514100825061,
						0.44678666115400945, 0.5567746713176865, 0.7073523972581507, 0.6427233555569606,
						0.5321787866473575, 1.2352273504694689, 1.6759255700039515, 1.3852286517768757,
						1.7456343241243544, 1.1982814418175418, 1.2457231736191916, 0.9480157532797421,
						0.773601524633383, 2.1908683183233757, 0.7911710734867028 },
				{ 0.16216785769227668, 0.15400675188336213, 0.6558045022065756, 0.3973167986895514, 0.5808829959825337,
						0.6949088796298145, 0.7252387481055351, 0.0, 1.221045549959678, 0.9680654535148255,
						0.6591204611863755, 0.48456057063432645, 0.5253118152550658, 0.8140085370957997,
						0.9284764420944434, 1.6846770578720511, 2.1346328921453, 1.6233408276174348, 2.1144798228585233,
						1.3957098214265318, 0.9832938674071189, 1.148301057346058, 1.4270843640893651,
						2.8467760797279893, 0.47738211265094227 },
				{ 1.1437496543531531, 1.3043183633983146, 0.7222462637085967, 0.8412683511694884, 0.6413597694791817,
						0.5548121131340912, 0.5332530206118581, 1.221045549959678, 0.0, 0.3984685760378889,
						0.9351935612463287, 1.0824425694759305, 1.2306979420315556, 1.089636739417874,
						0.8957397306379988, 0.8596656227415103, 1.2523716779738137, 1.1753126822393907,
						1.3869921102256388, 1.0592965650269637, 1.7472060341422864, 1.3155315781120562,
						0.7217502200156151, 1.9558948845603013, 1.1271512278342048 },
				{ 0.9375884968140991, 1.0841732067605565, 0.6894462173671562, 0.656752362117806, 0.45915647811452553,
						0.47317624848109996, 0.2508514100825061, 0.9680654535148255, 0.3984685760378889, 0.0,
						0.5485618755067266, 0.7196623066253751, 0.8611480327443488, 0.6916163404654795,
						0.5014684776223943, 1.2205258208194902, 1.6363604135309464, 1.4557357480526278,
						1.746469473877745, 1.295196993077698, 1.3563734370336409, 0.9270486302838321,
						0.5421564889908872, 1.946406549806509, 1.0310048691996527 },
				{ 0.7242829789596977, 0.8108246744533113, 0.8305773935226485, 0.5977262539081614, 0.5472486829392279,
						0.6798970403856874, 0.44678666115400945, 0.6591204611863755, 0.9351935612463286,
						0.5485618755067266, 0.0, 0.21548363129107034, 0.32736084560289613, 0.21489420567787612,
						0.2713311760572367, 1.681992080850229, 2.1220614125839274, 1.8088806835814177,
						2.190517453521386, 1.6088013815777638, 0.8121951678402174, 0.5607356457321332,
						0.831172044442856, 2.217979450099074, 0.9717848848900794 },
				{ 0.58325236121914, 0.6385082308891917, 0.8233854962031284, 0.5483079994125415, 0.5762400071317826,
						0.722042282604822, 0.5567746713176865, 0.48456057063432645, 1.0824425694759305,
						0.7196623066253751, 0.21548363129107034, 0.0, 0.1510658368374046, 0.329694771716296,
						0.47818474407333383, 1.7636474039351753, 2.212309072584642, 1.833855662504285,
						2.2547637282253894, 1.6213952896045931, 0.7045830812420859, 0.6673030476754521,
						1.0462882742854238, 2.4303995739421294, 0.8699022249764875 },
				{ 0.6530171748280855, 0.6732029177282913, 0.9571656590501431, 0.6736833056494976, 0.7220180179207759,
						0.8683802911814711, 0.7073523972581507, 0.5253118152550658, 1.2306979420315556,
						0.8611480327443488, 0.32736084560289613, 0.1510658368374046, 0.0, 0.35216010936262027,
						0.5559035276785246, 1.91257022124989, 2.361895995738257, 1.971172039606755, 2.4005860080803085,
						1.7556910146539808, 0.5584188840755832, 0.6414404272312024, 1.1466206378062433,
						2.501178508177665, 0.9590830674175058 },
				{ 0.9039377828009775, 0.9680067367115353, 1.0454614645897953, 0.808187697754327, 0.7611093461156536,
						0.8909264442687987, 0.6427233555569606, 0.8140085370957997, 1.089636739417874,
						0.6916163404654795, 0.21489420567787612, 0.329694771716296, 0.35216010936262027, 0.0,
						0.2417099382271496, 1.8729855558535886, 2.3068444086111577, 2.01863353403402,
						2.3874722924677303, 1.821079010229487, 0.6832846821980829, 0.35157104406967304,
						0.8355543427613215, 2.1534877829834667, 1.171395315750449 },
				{ 0.982967428458987, 1.0788771065232028, 0.9982548049041942, 0.8133951415093356, 0.7065331175870889,
						0.8109883960808932, 0.5321787866473575, 0.9284764420944434, 0.8957397306379988,
						0.5014684776223943, 0.2713311760572367, 0.47818474407333383, 0.5559035276785246,
						0.2417099382271496, 0.0, 1.716978044199293, 2.137503348735863, 1.9122749816410969,
						2.2401987808393815, 1.729783696574586, 0.9206872324658922, 0.42851727059814915,
						0.597822659656048, 1.952979021405012, 1.2057386295265393 },
				{ 1.5446890391087509, 1.693053994288486, 1.0288756243606219, 1.3052891760372345, 1.1937655187580505,
						1.0476868251565792, 1.2352273504694689, 1.6846770578720511, 0.8596656227415103,
						1.2205258208194902, 1.681992080850229, 1.7636474039351753, 1.91257022124989, 1.872985555853589,
						1.716978044199293, 0.0, 0.45280800358512163, 0.4911615782104511, 0.527937453070571,
						0.5587259305678346, 2.4675734565415315, 2.1452170719612096, 1.5628546440037043,
						2.570918792791232, 1.3420318485213483 },
				{ 1.992004996879986, 2.1374241353385464, 1.479230481279773, 1.7577279480091286, 1.6451559721232156,
						1.499406723879083, 1.6759255700039515, 2.1346328921453, 1.2523716779738137, 1.6363604135309464,
						2.1220614125839274, 2.212309072584642, 2.361895995738257, 2.3068444086111577, 2.137503348735863,
						0.45280800358512163, 0.0, 0.7145992304104118, 0.28910658393660843, 0.8880736595068649,
						2.9150940733730475, 2.5631005781441925, 1.9113217356240602, 2.7318544570338017,
						1.774430359470661 },
				{ 1.4650728165440203, 1.5866454454198238, 1.0149188750763556, 1.3039184378841802, 1.266892168882502,
						1.1290900663342653, 1.3852286517768757, 1.6233408276174348, 1.1753126822393907,
						1.4557357480526278, 1.8088806835814177, 1.833855662504285, 1.971172039606755, 2.01863353403402,
						1.9122749816410969, 0.4911615782104511, 0.7145992304104118, 0.0, 0.5495232763118331,
						0.2291287457346609, 2.526135603142713, 2.3331565990679715, 1.896008350401138, 3.024749232269641,
						1.1908434939776888 },
				{ 1.962441494668488, 2.0964194588610634, 1.4715305101992813, 1.7605249717251799, 1.678764685259441,
						1.5327254786304514, 1.7456343241243544, 2.1144798228585233, 1.3869921102256388,
						1.746469473877745, 2.190517453521386, 2.2547637282253894, 2.400586008080309, 2.3874722924677303,
						2.2401987808393815, 0.527937453070571, 0.28910658393660843, 0.5495232763118331, 0.0,
						0.7637844429401026, 2.9587767789230726, 2.668712601158967, 2.0827887212632694,
						2.9912829236608993, 1.711806097401127 },
				{ 1.2368813836806458, 1.3575386712089785, 0.7986424546536901, 1.0847130929493947, 1.0626099837178762,
						0.9304635612547123, 1.1982814418175418, 1.3957098214265318, 1.0592965650269637,
						1.295196993077698, 1.6088013815777638, 1.6213952896045931, 1.7556910146539808,
						1.821079010229487, 1.729783696574586, 0.5587259305678346, 0.8880736595068649,
						0.2291287457346609, 0.7637844429401026, 0.0, 2.308141922574677, 2.144376092741614,
						1.7680766446309344, 2.970032871890461, 0.9619353083201395 },
				{ 1.1389831399462942, 1.0991524727384423, 1.5112193162520122, 1.2234396987875198, 1.28002332784617,
						1.4262000563381405, 1.2457231736191916, 0.9832938674071189, 1.7472060341422868,
						1.3563734370336409, 0.8121951678402175, 0.7045830812420859, 0.5584188840755832,
						0.6832846821980829, 0.9206872324658922, 2.4675734565415315, 2.9150940733730475,
						2.526135603142713, 2.9587767789230726, 2.308141922574677, 0.0, 0.693753252276295,
						1.4927398006578647, 2.6825423509709747, 1.457326296246854 },
				{ 1.2494762123654162, 1.3012622211979623, 1.385067767413847, 1.1580290233387596, 1.095699614382259,
						1.2161180266948812, 0.9480157532797421, 1.148301057346058, 1.3155315781120562,
						0.9270486302838321, 0.5607356457321332, 0.6673030476754521, 0.6414404272312024,
						0.35157104406967304, 0.42851727059814915, 2.1452170719612096, 2.5631005781441925,
						2.3331565990679715, 2.668712601158967, 2.144376092741614, 0.693753252276295, 0.0,
						0.8587656151678817, 1.9920834134721166, 1.5226225604441352 },
				{ 1.4320735530109943, 1.5625028978624262, 1.2312585764149417, 1.176942503030015, 0.9920729720881399,
						1.0151172804223885, 0.773601524633383, 1.4270843640893651, 0.7217502200156151,
						0.5421564889908871, 0.831172044442856, 1.0462882742854238, 1.1466206378062433,
						0.8355543427613215, 0.597822659656048, 1.5628546440037043, 1.9113217356240602,
						1.896008350401138, 2.0827887212632694, 1.7680766446309348, 1.4927398006578647,
						0.8587656151678817, 0.0, 1.425120515101927, 1.5647391072327554 },
				{ 2.857158550607521, 2.9855667574534803, 2.6152740570864283, 2.597776751264347, 2.405325653464592,
						2.4048811954008147, 2.1908683183233757, 2.8467760797279893, 1.9558948845603013,
						1.946406549806509, 2.217979450099074, 2.4303995739421294, 2.501178508177665, 2.1534877829834667,
						1.952979021405012, 2.570918792791232, 2.7318544570338017, 3.024749232269641, 2.9912829236608993,
						2.970032871890461, 2.6825423509709747, 1.9920834134721166, 1.425120515101927, 0.0,
						2.9772866921796144 },
				{ 0.31839091450263013, 0.4022628431313873, 0.4080102381169919, 0.3987289162562041, 0.5730981973307111,
						0.5863087646960253, 0.7911710734867028, 0.47738211265094227, 1.1271512278342048,
						1.0310048691996527, 0.9717848848900794, 0.8699022249764875, 0.9590830674175058,
						1.171395315750449, 1.2057386295265393, 1.3420318485213483, 1.774430359470661,
						1.1908434939776888, 1.711806097401127, 0.9619353083201395, 1.457326296246854,
						1.5226225604441352, 1.5647391072327554, 2.9772866921796144, 0.0 },

		};

		ArrayList<String> strListCoordinate = new ArrayList<String>();
		strListCoordinate.add("10.853660362969093, 106.7871288598472");
		strListCoordinate.add("10.854249966939836, 106.78848556115487");
		strListCoordinate.add("10.853261674064578, 106.78238599535801");
		strListCoordinate.add("10.852176217750191, 106.78480811722031");
		strListCoordinate.add("10.850860914954634, 106.78346762553727");
		
		strListCoordinate.add("10.851324543031614, 106.78221235179959");
		strListCoordinate.add("10.848922098841676, 106.7830921162995");
		strListCoordinate.add("10.852869571044405, 106.7883760254088");
		strListCoordinate.add("10.848180596291106, 106.77826946222977");
		strListCoordinate.add("10.847095272568755, 106.78174560488794");

		strListCoordinate.add("10.847158495417899, 106.78676669983865");
		strListCoordinate.add("10.84851778343675, 106.78817217747157");
		strListCoordinate.add("10.84828596731165, 106.7895347395629");
		strListCoordinate.add("10.845567382977551, 106.78788249891672");
		strListCoordinate.add("10.844924613281936, 106.78576891833944");
		
		strListCoordinate.add("10.853912726249177, 106.77299087466669");
		strListCoordinate.add("10.85470299287872, 106.76892464606344");
		strListCoordinate.add("10.858074773711202, 106.77449291165735");
		strListCoordinate.add("10.857231832078526, 106.76953618971883");
//		strListCoordinate.add("10.85754793546952, 106.7765206615413");
//
//		strListCoordinate.add("10.846060901021785, 106.79411700734532");
//		strListCoordinate.add("10.842556991590355, 106.78886332086951");
//		strListCoordinate.add("10.842275269082089, 106.7810075829348");
//		strListCoordinate.add("10.830886238508313, 106.775031825743");
		// strListCoordinate.add("10.855746690452987, 106.78513333087042");

		ArrayList<Position> listOfPositions = convertCoordinatesIntoPositions(strListCoordinate);
		Position storeCoordinate = new Position("10.855746690452987, 106.78513333087042");

		HashMap<PositionPair, Double> distances = convert2DArrayIntoHashMap(distances2d, (numOfPositions + 1),
				listOfPositions, storeCoordinate);

		PSO pso = new PSO(numOfParticles, numOfPositions, numOfShippers, distances, storeCoordinate, wMax,  c1,
				c2);
		pso.solvePSO(listOfPositions);

		HashMap<String, SubSolution> finalSolution = pso.getBestGlobalParticle().getPersonalBestSolution();

		for (int i = 0; i < numOfShippers; i++) {
			SubSolution currentSolution = finalSolution.get(String.valueOf(i));
			List<Position> list = currentSolution.getTour();
			int num = list.size();
			System.out.print("Shipper: " + i + "| ");
			for (int j = 0; j < num; j++) {
				System.out.print(list.get(j).getPriority() + " ");
			}
			System.out.println("distances cost: "+currentSolution.getCost());
			System.out.println();
		}

	}

//===Server API================================================================================================
	public static ArrayList<Position> convertCoordinatesIntoPositions(ArrayList<String> coordinates) {
		ArrayList<Position> listOfPositions = new ArrayList<>();
		int num = coordinates.size();
		for (int i = 0; i < num; i++) {
			listOfPositions.add(new Position(coordinates.get(i), (i + 1)));
		}
		return listOfPositions;
	}

	public static HashMap<PositionPair, Double> convert2DArrayIntoHashMap(double[][] distances2d, int numOfPositions,
			ArrayList<Position> listOfPositions, Position storeCoordinate) {

		HashMap<PositionPair, Double> distances = new HashMap<>();
		ArrayList<Position> cloneListOfPositions = new ArrayList<Position>(listOfPositions);
		cloneListOfPositions.add(storeCoordinate);

		for (int i = 0; i < numOfPositions; i++) {
			for (int j = 0; j < numOfPositions; j++) {
				PositionPair posKey = new PositionPair(cloneListOfPositions.get(i), cloneListOfPositions.get(j));
				distances.put(posKey, distances2d[i][j]);
			}
		}
		return distances;
	}

}
