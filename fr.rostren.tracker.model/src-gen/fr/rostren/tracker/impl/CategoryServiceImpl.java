/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
/**
 */
package fr.rostren.tracker.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import fr.rostren.tracker.Category;
import fr.rostren.tracker.CategoryService;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Category Service</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.CategoryServiceImpl#getCategory <em>Category</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CategoryServiceImpl extends EObjectImpl implements CategoryService {
	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected Category category;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.CATEGORY_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Category getCategory() {
		if (category != null && category.eIsProxy()) {
			InternalEObject oldCategory=(InternalEObject)category;
			category=(Category)eResolveProxy(oldCategory);
			if (category != oldCategory) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.CATEGORY_SERVICE__CATEGORY, oldCategory, category));
			}
		}
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category basicGetCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCategory(Category newCategory) {
		Category oldCategory=category;
		category=newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.CATEGORY_SERVICE__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addDescription(String description) {
		category.setDescription(description);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeDescription() {
		category.setDescription(null);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addOperationTitle(String title) {
		Optional<OperationTitle> titleOpt=TrackerUtils.getTrackerService(category).findOperationTitle(title);
		if (!titleOpt.isPresent()) {
			OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle(TrackerUtils.getTracker(category), title);
			category.getOperationTitles().add(operationTitle);
			return;
		}
		titleOpt.ifPresent(any -> {
			if (!category.getOperationTitles().contains(any)) {
				category.getOperationTitles().add(any);
			}
		});
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeOperationTitle(String title) {
		List<OperationTitle> titles=category.getOperationTitles().stream()//
				.filter(operationTitle -> operationTitle.getTitle() == null || title.equals(operationTitle.getTitle()))//
				.collect(Collectors.toList());
		category.getOperationTitles().removeAll(titles);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeOperationTitle(OperationTitle title) {
		if (category.getOperationTitles().contains(title)) {
			category.getOperationTitles().remove(title);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns a list containing the category itself and all its subCategories.
	 * @param category the {@link Category} instance
	 * @return the categories
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Category> getCategories() {
		if (category instanceof IncomeCategory) {
			EList<Category> categories=new BasicEList<>(((IncomeCategory)category).getIncomes().stream()//
					.flatMap(subCategory -> TrackerUtils.getCategoryService(subCategory).getCategories().stream())//
					.collect(Collectors.toList()));
			categories.add(0, category);
			return categories;
		}
		if (category instanceof SpendingCategory) {
			EList<Category> categories=new BasicEList<>(((SpendingCategory)category).getSpendings().stream()//
					.flatMap(subCategory -> TrackerUtils.getCategoryService(subCategory).getCategories().stream())//
					.collect(Collectors.toList()));
			categories.add(0, category);
			return categories;
		}
		throw new IllegalArgumentException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the concerned category
	 * @param category the category to test
	 * @param title the category title as a {@link String}
	 * @return the category
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Category findCategory(String title) {
		if (title.equals(category.getTitle())) {
			return category;
		}
		if (category instanceof IncomeCategory) {
			for (IncomeCategory subCategory: ((IncomeCategory)category).getIncomes()) {
				return TrackerUtils.getCategoryService(subCategory).findCategory(title);
			}
		}
		if (category instanceof SpendingCategory) {
			for (SpendingCategory subCategory: ((SpendingCategory)category).getSpendings()) {
				return TrackerUtils.getCategoryService(subCategory).findCategory(title);
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param category the category
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isTitleUnique(String title) {
		//FIXME validate the next java8 code
		if (!StringUtils.isEmpty(category.getTitle()) && StringUtils.deleteWhitespace(category.getTitle()).equals(StringUtils.deleteWhitespace(title))) {
			return false;
		}

		if (category instanceof IncomeCategory) {
			return ((IncomeCategory)category).getIncomes().stream()//
					.allMatch(subCategory -> TrackerUtils.getCategoryService(subCategory).isTitleUnique(title));
		}
		if (category instanceof SpendingCategory) {
			return ((SpendingCategory)category).getSpendings().stream()//
					.allMatch(subCategory -> TrackerUtils.getCategoryService(subCategory).isTitleUnique(title));
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 * @param category the category
	 * @return <code>true</code> if the category is the undefined category, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isUndefinedCategory() {
		if (category == null) {
			throw new IllegalArgumentException("The category cannot be null.");//$NON-NLS-1$
		}
		return TrackerUtils.UNDEFINED_INCOME_TITLE.equals(category.getTitle()) || TrackerUtils.UNDEFINED_SPENDING_TITLE.equals(category.getTitle());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				if (resolve)
					return getCategory();
				return basicGetCategory();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				setCategory((Category)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				setCategory((Category)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TrackerPackage.CATEGORY_SERVICE__CATEGORY:
				return category != null;
		}
		return super.eIsSet(featureID);
	}

} // CategoryServiceImpl
