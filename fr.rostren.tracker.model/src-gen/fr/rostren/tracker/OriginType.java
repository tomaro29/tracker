/**
 */
package fr.rostren.tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Origin Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see fr.rostren.tracker.TrackerPackage#getOriginType()
 * @model
 * @generated
 */
public enum OriginType implements Enumerator {
    /**
     * The '<em><b>PDF FILE</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #PDF_FILE_VALUE
     * @generated
     * @ordered
     */
    PDF_FILE(0, "PDF_FILE", "PDF_FILE"),

    /**
     * The '<em><b>MANUAL</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #MANUAL_VALUE
     * @generated
     * @ordered
     */
    MANUAL(1, "MANUAL", "MANUAL");

    /**
     * The '<em><b>PDF FILE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PDF FILE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PDF_FILE
     * @model
     * @generated
     * @ordered
     */
    public static final int PDF_FILE_VALUE = 0;

    /**
     * The '<em><b>MANUAL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MANUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #MANUAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MANUAL_VALUE = 1;

    /**
     * An array of all the '<em><b>Origin Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final OriginType[] VALUES_ARRAY = new OriginType[] { PDF_FILE, MANUAL, };

    /**
     * A public read-only list of all the '<em><b>Origin Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<OriginType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Origin Type</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static OriginType get(String literal) {
	for (int i = 0; i < VALUES_ARRAY.length; ++i) {
	    OriginType result = VALUES_ARRAY[i];
	    if (result.toString().equals(literal)) {
		return result;
	    }
	}
	return null;
    }

    /**
     * Returns the '<em><b>Origin Type</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static OriginType getByName(String name) {
	for (int i = 0; i < VALUES_ARRAY.length; ++i) {
	    OriginType result = VALUES_ARRAY[i];
	    if (result.getName().equals(name)) {
		return result;
	    }
	}
	return null;
    }

    /**
     * Returns the '<em><b>Origin Type</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static OriginType get(int value) {
	switch (value) {
	case PDF_FILE_VALUE:
	    return PDF_FILE;
	case MANUAL_VALUE:
	    return MANUAL;
	}
	return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    private OriginType(int value, String name, String literal) {
	this.value = value;
	this.name = name;
	this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getValue() {
	return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
	return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLiteral() {
	return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string
     * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
	return literal;
    }

} // OriginType
