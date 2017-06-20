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

import fr.rostren.tracker.Account;
import fr.rostren.tracker.BoockletAccount;
import fr.rostren.tracker.CategoriesRepository;
import fr.rostren.tracker.Category;
import fr.rostren.tracker.CheckingAccount;
import fr.rostren.tracker.IncomeCategory;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Owner;
import fr.rostren.tracker.SpendingCategory;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerFactory;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.TrackerService;
import fr.rostren.tracker.model.utils.TrackerUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.rostren.tracker.impl.TrackerServiceImpl#getTracker <em>Tracker</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TrackerServiceImpl extends EObjectImpl implements TrackerService {
	/**
	 * The cached value of the '{@link #getTracker() <em>Tracker</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracker()
	 * @generated
	 * @ordered
	 */
	protected Tracker tracker;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackerServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TrackerPackage.Literals.TRACKER_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tracker getTracker() {
		if (tracker != null && tracker.eIsProxy()) {
			InternalEObject oldTracker=(InternalEObject)tracker;
			tracker=(Tracker)eResolveProxy(oldTracker);
			if (tracker != oldTracker) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrackerPackage.TRACKER_SERVICE__TRACKER, oldTracker, tracker));
				}
			}
		}
		return tracker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tracker basicGetTracker() {
		return tracker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTracker(Tracker newTracker) {
		Tracker oldTracker=tracker;
		tracker=newTracker;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, TrackerPackage.TRACKER_SERVICE__TRACKER, oldTracker, tracker));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Adds an income category instance to the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addIncomeCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		Optional<IncomeCategory> any=repository.getIncome().getIncomes().stream().filter(category -> category.getTitle().equals(title)).findAny();
		if (!any.isPresent()) {
			IncomeCategory incomeCategory=TrackerFactory.eINSTANCE.createIncomeCategory();
			incomeCategory.setTitle(title);
			repository.getIncome().getIncomes().add(incomeCategory);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Adds a spending category instance to the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addSpendingCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		Optional<SpendingCategory> any=repository.getSpending().getSpendings().stream().filter(category -> category.getTitle().equals(title)).findAny();
		if (!any.isPresent()) {
			SpendingCategory spendingCategory=TrackerFactory.eINSTANCE.createSpendingCategory();
			spendingCategory.setTitle(title);
			repository.getSpending().getSpendings().add(spendingCategory);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Removes the given category from the repository
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteCategory(Category category) {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		if (category instanceof IncomeCategory && repository.getIncome().getIncomes().contains(category)) {
			repository.getIncome().getIncomes().remove(category);
		}
		if (category instanceof SpendingCategory && repository.getSpending().getSpendings().contains(category)) {
			repository.getSpending().getSpendings().remove(category);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Removes all categories titled by the given title
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteCategory(String title) {
		CategoriesRepository repository=tracker.getCategoriesRepository();

		List<IncomeCategory> incomes=repository.getIncome().getIncomes();
		List<Category> matches=incomes.stream().filter(category -> category.getTitle().equals(title)).collect(Collectors.toList());
		incomes.removeAll(matches);

		List<SpendingCategory> spendings=repository.getSpending().getSpendings();
		matches=spendings.stream().filter(category -> category.getTitle().equals(title)).collect(Collectors.toList());
		spendings.removeAll(matches);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addOwner(String firstName, String lastName) {
		Owner owner=TrackerFactory.eINSTANCE.createOwner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		tracker.getOwners().add(owner);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOwner(Owner owner) {
		if (tracker.getOwners().contains(owner)) {
			tracker.getOwners().remove(owner);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOwner(String firstName, String lastName) {
		List<Owner> owners=tracker.getOwners().stream()//
				.filter(owner -> owner.getFirstName().equals(firstName) && owner.getLastName().equals(lastName))//
				.collect(Collectors.toList());
		tracker.getOwners().removeAll(owners);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addOrigin(String identifier) {
		Origin origin=TrackerFactory.eINSTANCE.createOrigin();
		origin.setIdentifier(identifier);
		tracker.getOriginsRepository().getOrigins().add(origin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOrigin(Origin origin) {
		if (tracker.getOriginsRepository().getOrigins().contains(origin)) {
			tracker.getOriginsRepository().getOrigins().remove(origin);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOrigin(String identifier) {
		List<Origin> origins=tracker.getOriginsRepository().getOrigins().stream()//
				.filter(origin -> origin.getIdentifier().equals(identifier))//
				.collect(Collectors.toList());
		tracker.getOriginsRepository().getOrigins().removeAll(origins);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void addOperationTitle(String title) {
		OperationTitle operationTitle=TrackerFactory.eINSTANCE.createOperationTitle();
		operationTitle.setTitle(title);
		tracker.getOperationsTitlesRepositories().getOperationsTitles().add(operationTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOperationTitle(OperationTitle operationTitle) {
		if (tracker.getOperationsTitlesRepositories().getOperationsTitles().contains(operationTitle)) {
			tracker.getOperationsTitlesRepositories().getOperationsTitles().remove(operationTitle);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void deleteOperationTitle(String title) {
		List<OperationTitle> titles=tracker.getOperationsTitlesRepositories().getOperationsTitles().stream()//
				.filter(operationTitle -> operationTitle.getTitle().equals(title))//
				.collect(Collectors.toList());
		tracker.getOperationsTitlesRepositories().getOperationsTitles().removeAll(titles);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param tracker the tracker
	 * @return the categories repository if any, a new one otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public CategoriesRepository getCategoriesRepository() {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		if (repository == null) {
			repository=TrackerFactory.eINSTANCE.createCategoriesRepository();
			tracker.setCategoriesRepository(repository);
		}
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the categories
	 * @param repository the {@link CategoriesRepository} instance
	 * @return the categories
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Category> getAllCategories() {
		CategoriesRepository repository=tracker.getCategoriesRepository();
		IncomeCategory income=repository.getIncome();
		EList<Category> categories=new BasicEList<>();
		if (income != null) {
			categories.addAll(income.getIncomes().stream()//
					.flatMap(category -> TrackerUtils.getCategoryService(category).getCategories().stream())//
					.collect(Collectors.toList()));
			categories.add(0, income);
		}
		SpendingCategory spending=repository.getSpending();
		if (spending != null) {
			categories.addAll(spending.getSpendings().stream()//
					.flatMap(category -> TrackerUtils.getCategoryService(category).getCategories().stream())//
					.collect(Collectors.toList()));
			categories.add(0, spending);
		}
		return categories;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public EList<OperationTitle> getOperationsTitles() {
		return tracker.getOperationsTitlesRepositories().getOperationsTitles();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public EList<Origin> getOrigins() {
		return tracker.getOriginsRepository().getOrigins();
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the accounts
	 * @param tracker the {@link Tracker} instance
	 * @return the accounts
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Account> getAccounts() {
		return new BasicEList<>(tracker.getOwners()//
				.stream()//
				.flatMap(owner -> owner.getAccounts().stream())//
				.collect(Collectors.toList()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the categories
	 * @param tracker the {@link Tracker} instance
	 * @return the categories
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Category> getCategories() {
		CategoriesRepository repository=getCategoriesRepository();
		IncomeCategory income=repository.getIncome();
		SpendingCategory spending=repository.getSpending();

		EList<Category> categories=new BasicEList<>();
		if (income != null) {
			categories.add(income);
		}
		if (spending != null) {
			categories.add(spending);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Returns the available years
	 * @param tracker the {@link Tracker} instance
	 * @return the years
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<Integer> findYears() {
		EList<Integer> years=new BasicEList<>();
		getAccounts().stream()//
				.filter(account -> account instanceof CheckingAccount)//
				.forEach(account -> years.addAll(//
						((CheckingAccount)account).getOperations().stream()//
								.filter(operation -> operation.getDate() != null)//
								.mapToInt(operation -> operation.getDate().getYear())//
								.boxed()//
								.collect(Collectors.toSet())//
		));
		getAccounts().stream()//
				.filter(account -> account instanceof BoockletAccount)//
				.forEach(account -> years.addAll(//
						((BoockletAccount)account).getTransfers().stream()//
								.filter(transfer -> transfer.getDate() != null)//
								.mapToInt(transfer -> transfer.getDate().getYear())//
								.boxed()//
								.collect(Collectors.toSet())//
		));
		return years;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isOperationTitleUnique(String title) {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The title to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		return tracker.getOperationsTitlesRepositories().getOperationsTitles().stream()//
				.map(opTitle -> opTitle.getTitle())//
				.noneMatch(opTitleTitle -> !StringUtils.isEmpty(opTitleTitle)	&& //
											StringUtils.deleteWhitespace(opTitleTitle).equals(StringUtils.deleteWhitespace(title)));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param title the title
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isCategoryTitleUnique(String title) {
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			throw new IllegalArgumentException("The title to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		return getAllCategories().stream()//
				.allMatch(category -> TrackerUtils.getCategoryService(category).isTitleUnique(title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param identifier the identifier
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isOriginIdentifierUnique(String identifier) {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}

		return tracker.getOriginsRepository().getOrigins().stream()//
				.noneMatch(origin -> StringUtils.deleteWhitespace(origin.getIdentifier()).equals(StringUtils.deleteWhitespace(identifier)));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param firstName the firstName
	 * @param lastName the lastName
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isOwnerIdentifierUnique(String firstName, String lastName) {
		if (StringUtils.isEmpty(firstName) || StringUtils.isBlank(firstName)) {
			throw new IllegalArgumentException("The first name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(lastName) || StringUtils.isBlank(lastName)) {
			throw new IllegalArgumentException("The last name to check cannot be null, empty or blank.");//$NON-NLS-1$
		}

		return tracker.getOwners().stream()//
				.noneMatch(owner -> StringUtils.deleteWhitespace(firstName).equals(StringUtils.deleteWhitespace(owner.getFirstName()))
									&& StringUtils.deleteWhitespace(lastName).equals(StringUtils.deleteWhitespace(owner.getLastName())));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <code>true</code> if is unique, <code>false</code> otherwise.
	 * @param tracker the tracker
	 * @param identifier the identifier
	 * @return <code>true</code> if is unique, <code>false</code> otherwise.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isAccountIdentifierUnique(String identifier) {
		if (StringUtils.isEmpty(identifier) || StringUtils.isBlank(identifier)) {
			throw new IllegalArgumentException("The identifier to check cannot be null, empty or blank.");//$NON-NLS-1$
		}
		return tracker.getOwners().stream()//
				.noneMatch(owner -> owner.getAccounts().stream()//
						.noneMatch(account -> account.getIdentifier() == Integer.parseInt(identifier)));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param tracker the opened tracker root
	 * @param accountName the account name
	 * @return the account
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Account findAccount(String name) {
		return getAccounts()//
				.stream()//
				.filter(account -> name.equals(account.getName()))//
				.map(Account.class::cast)//
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public Optional<OperationTitle> findOperationTitle(String title) {
		if (tracker == null) {
			throw new IllegalArgumentException("The tracker cannot be null.");//$NON-NLS-1$
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			return null;
		}
		OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();
		if (repository == null) {
			repository=TrackerFactory.eINSTANCE.createOperationsTitleRepository();
			tracker.setOperationsTitlesRepositories(repository);
		}
		return repository.getOperationsTitles().stream()//
				.filter(operationTitle -> title.equals(operationTitle.getTitle()))//
				.findFirst();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				if (resolve) {
					return getTracker();
				}
				return basicGetTracker();
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				setTracker((Tracker)newValue);
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				setTracker((Tracker)null);
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
			case TrackerPackage.TRACKER_SERVICE__TRACKER:
				return tracker != null;
		}
		return super.eIsSet(featureID);
	}

} //TrackerServiceImpl
