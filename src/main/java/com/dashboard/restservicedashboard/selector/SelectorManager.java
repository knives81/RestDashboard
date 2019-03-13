package com.dashboard.restservicedashboard.selector;

import com.dashboard.commondashboard.Entity;
import com.dashboard.commondashboard.EntityConfAbstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class SelectorManager {
	
	private static final Logger log = LoggerFactory.getLogger(SelectorManager.class);

	private List<SelectorHelper> helpers;

	@Autowired
	ConfigurationService configurationService;

	public List<Selector> computeSelectors() {

		helpers = initSelectorHelpers();

		List<Selector> selectors = new ArrayList<>();
		for (Entity.EntityType entityType : Entity.EntityType.values()) {

			List<Option> options = new ArrayList<>();

			List<String> tagNames = helpers.stream()
					.filter((s) -> entityType.equals(s.getEntityType()))
					.filter(distinctByKey(SelectorHelper::getEntityTagName))
					.sorted(Comparator.comparing(SelectorHelper::getOrderTagName))
					.map(SelectorHelper::getEntityTagName)
					.collect(Collectors.toList());

			for(String tagName : tagNames) {
				List<String> tagValues = helpers.stream()
						.filter((s) -> entityType.equals(s.getEntityType()))
						.filter((s) -> tagName.equals(s.getEntityTagName()))
						.filter(distinctByKey(SelectorHelper::getEntityTagValue))
						.sorted(Comparator.comparing(SelectorHelper::getEntityTagValue))
						.map(SelectorHelper::getEntityTagValue)
						.collect(Collectors.toList());

				System.out.println(tagName);
				System.out.println(tagValues);
				options.add(Option.buildOption(tagName, tagValues));
			}

			options.add(Option.buildOptionChartType());
			Selector selector = new Selector();
			selector.setEntityType(entityType);
			selector.setItems(options);
			selectors.add(selector);
		}
		return selectors;
	}

	private List<SelectorHelper> initSelectorHelpers() {
		List<SelectorHelper> helpers = new ArrayList<>();
		for (Entity.EntityType entityType : Entity.EntityType.values()) {

			EntityConfAbstract entityConf = configurationService.getEntityConf(entityType);
			if(entityConf == null) {
				break;
			}

			for(int indexNode = 0; indexNode<entityConf.getConfPositionIndexes().size(); indexNode++) {
				Integer confPositionIndex = entityConf.getConfPositionIndexes().get(indexNode);

				for(int indexTagName =0; indexTagName<entityConf.getTagNames().size(); indexTagName++) {
					String tagName = entityConf.getTagNames().get(indexTagName);
					String tagValue = entityConf.getTags().get(indexNode).get(indexTagName);

					SelectorHelper helper = new SelectorHelper();
					helper.setEntityType(entityType);
					helper.setConfPositionIndex(confPositionIndex);
					helper.setOrderTagName(indexTagName);
					helper.setEntityTagName(tagName);
					helper.setEntityTagValue(tagValue);
					helpers.add(helper);
				}
			}
		}
		return helpers;
	}

	public List<Integer> getConfPositionIndex(Selector selector) {

		if(helpers==null) {
			helpers = initSelectorHelpers();
		}


		List<Integer> confPositionIndexes = new ArrayList<>();

		Entity.EntityType entityType = selector.getEntityType();
		List<SelectorHelper> helpersFilteredByType = helpers.stream()
				.filter(s -> entityType.equals(s.getEntityType()))
				.collect(Collectors.toList());

        List<Set<Integer>> confPositionIndexesByOption = new ArrayList<>();

		for (Option option : selector.getItems()) {
			String tagName = option.getTagName();
			String tagValueSelected = option.getTagSelected();

            if (!SelectorConstant.CHART_TYPE_LABEL.equals(tagName)) {

                if (!tagValueSelected.equals(SelectorConstant.ALL_LABEL)) {
                    Set<Integer> confPositionIndexByOption = helpersFilteredByType.stream()
                            .filter(s -> tagName.equals(s.getEntityTagName()))
                            .filter(s -> tagValueSelected.equals(s.getEntityTagValue()))
                            .map(SelectorHelper::getConfPositionIndex)
                            .collect(Collectors.toSet());
                    confPositionIndexesByOption.add(confPositionIndexByOption);
                    System.out.println(confPositionIndexByOption);
                } else {
                    Set<Integer> confPositionIndexByOption = helpersFilteredByType.stream()
                            .filter(s -> tagName.equals(s.getEntityTagName()))
                            .map(SelectorHelper::getConfPositionIndex)
                            .collect(Collectors.toSet());
                    confPositionIndexesByOption.add(confPositionIndexByOption);
                    System.out.println(confPositionIndexByOption);
                }
            }
		}
        Set<Integer> confPositionIndexResult = confPositionIndexesByOption.get(0);
        for(int i = 1; i<confPositionIndexesByOption.size(); i++) {
            confPositionIndexResult.retainAll(confPositionIndexesByOption.get(i));
        }
        System.out.println(confPositionIndexResult);
        return new ArrayList<Integer>(confPositionIndexResult);
	}

	public String getTitleFromSelected(Selector selector) {
		String selectorTitle = "";
		for(Option option : selector.getItems()) {
			selectorTitle += option.getTagSelected();
			selectorTitle += " ";
		}
		return selectorTitle;
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}


}
