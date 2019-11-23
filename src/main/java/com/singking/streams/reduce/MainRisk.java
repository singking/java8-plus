package com.singking.streams.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This is the main outcome risk trigger we need to replace for HRM:
 * https://bitbucket.lvs.co.uk:8443/projects/HYD/repos/abp-hydra-fork/browse/agp-database/abpdb/liquibase/dbobjects/triggers/b_risks_aggregator/b_risks_aggregator_11.xml
 *
 */
public class MainRisk {

	public static RiskData createRiskData(long outcomeId, int lineId, int betCount, int liability, int staked) {
		return new RiskData(lineId, outcomeId, betCount, liability, staked);
	}

	public static void main(String[] args) {

		List<String> items = Arrays.asList("apple", "banana", "orange", "papaya", "pineapple", "plum", "cherries");
		Map<String, Integer> fruitMap = items.stream().collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(fruitMap);
		System.out.println();

		List<RiskData> risks = new ArrayList<>();
		risks.add(createRiskData(1L, 1, 1, 10, 1000));
		risks.add(createRiskData(2093863L, 2, 3, 30, 3000));
		risks.add(createRiskData(1L, 1, 2, 20, 2000));
		risks.add(createRiskData(1L, 1, 3, 30, 3000));
		risks.add(createRiskData(1L, 2, 3, 30, 3000));
		risks.add(createRiskData(2093863L, 2, 3, 30, 3000));
		risks.add(createRiskData(2093863L, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));
		risks.add(createRiskData(5, 2, 3, 30, 3000));

		RiskData sum = risks.stream().reduce(new RiskData(1, 3L), RiskDataAddition::compact);
		System.out.println(sum);

		Map<RiskKey, List<RiskData>> riskDataMap = risks.stream().collect(Collectors.groupingBy(r -> r.getKey()));
		System.out.println(riskDataMap);
		System.out.println();

		System.out.println("--");
		System.out.println();
		for (Entry<RiskKey, List<RiskData>> riskDataList : riskDataMap.entrySet()) {
			System.out.println(riskDataList.getKey());
			for (RiskData riskData : riskDataList.getValue()) {
				System.out.println(" - " + riskData);
			}

			RiskData seedRiskData = new RiskData(riskDataList.getKey().getLineId(),
					riskDataList.getKey().getOutcomeId());

			RiskData aggregatedRiskData = riskDataList.getValue().stream().reduce(seedRiskData,
					RiskDataAddition::compact);

			System.out.println();
			System.out.println(" - aggregatedRiskData: " + aggregatedRiskData);
			System.out.println();
		}
	}
}
