/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.model;

import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.impl.TrackerFactoryImpl;
import fr.rostren.tracker.model.utils.TrackerUtils;

public class TrackerFactoryUtils {

	private final TrackerFactory factory;

	/**
	 * Constructor.
	 * @param factory the factory
	 */
	public TrackerFactoryUtils(TrackerFactory factory) {
		this.factory=factory;
	}

	/**
	 * Returns the undefined income category
	 * @param repository the repository
	 * @return the undefined income category
	 */
	public Category getUndefinedIncomeCategory(CategoriesRepository repository) {
		IncomeCategory income=repository.getIncome();
		if (income == null) {
			income=TrackerFactory.eINSTANCE.createIncomeCategory();
			repository.setIncome(income);
		}

		Optional<IncomeCategory> findAny=income.getIncomes().stream()//
				.filter(category -> TrackerUtils.getCategoryService(category).isUndefinedCategory())//
				.findAny();
		return findAny.orElse(factory.createCategory(income));
	}

	/**
	 * Returns the undefined spending category
	 * @param repository the repository
	 * @return the undefined spending category
	 */
	public Category getUndefinedSpendingCategory(CategoriesRepository repository) {
		SpendingCategory spending=repository.getSpending();
		if (spending == null) {
			spending=TrackerFactory.eINSTANCE.createSpendingCategory();
			repository.setSpending(spending);
		}
		return spending.getSpendings().stream()//
				.filter(category -> TrackerUtils.getCategoryService(category).isUndefinedCategory())//
				.findAny().orElse(factory.createCategory(spending));
	}

	/**
	 * Adds an {@link OperationTitle} instance to the given operation
	 * @param object the given account
	 * @param operation the given operation
	 */
	public void addOperationTitle(EObject object, Operation operation) {
		EObject rootContainer=EcoreUtil.getRootContainer(object);
		if (!(rootContainer instanceof Tracker)) {
			return;
		}

		// Add a default operation title
		OperationsTitleRepository repository=((Tracker)rootContainer).getOperationsTitlesRepositories();
		if (repository == null) {
			return;
		}

		EList<OperationTitle> operationsTitles=repository.getOperationsTitles();
		if (!operationsTitles.isEmpty()) {
			for (OperationTitle operationTitle: operationsTitles) {
				if (operationTitle.getTitle() == null) {
					TrackerFactoryImpl.setDefaultOperationTitle(operationTitle);
				}
			}
		}
		operation.setOperationTitle(TrackerFactoryImpl.getDefaultOperationTitle());
	}
}
