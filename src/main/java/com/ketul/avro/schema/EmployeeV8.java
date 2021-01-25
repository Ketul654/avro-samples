/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.ketul.avro.schema;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** This is Avro schema for Employee */
@org.apache.avro.specific.AvroGenerated
public class EmployeeV8 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 511330182683586613L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"EmployeeV8\",\"namespace\":\"com.ketul.avro.schema\",\"doc\":\"This is Avro schema for Employee\",\"fields\":[{\"name\":\"employee_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Employee Id\"},{\"name\":\"fist_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"First name of the employee\"},{\"name\":\"last_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Last name of the employee\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Age of the employee\"},{\"name\":\"salary\",\"type\":\"float\",\"doc\":\"Salary\"},{\"name\":\"email_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Employee email id\",\"default\":\"abc@xyz.com\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<EmployeeV8> ENCODER =
      new BinaryMessageEncoder<EmployeeV8>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<EmployeeV8> DECODER =
      new BinaryMessageDecoder<EmployeeV8>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<EmployeeV8> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<EmployeeV8> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<EmployeeV8> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<EmployeeV8>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this EmployeeV8 to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a EmployeeV8 from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a EmployeeV8 instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static EmployeeV8 fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Employee Id */
   private java.lang.String employee_id;
  /** First name of the employee */
   private java.lang.String fist_name;
  /** Last name of the employee */
   private java.lang.String last_name;
  /** Age of the employee */
   private int age;
  /** Salary */
   private float salary;
  /** Employee email id */
   private java.lang.String email_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public EmployeeV8() {}

  /**
   * All-args constructor.
   * @param employee_id Employee Id
   * @param fist_name First name of the employee
   * @param last_name Last name of the employee
   * @param age Age of the employee
   * @param salary Salary
   * @param email_id Employee email id
   */
  public EmployeeV8(java.lang.String employee_id, java.lang.String fist_name, java.lang.String last_name, java.lang.Integer age, java.lang.Float salary, java.lang.String email_id) {
    this.employee_id = employee_id;
    this.fist_name = fist_name;
    this.last_name = last_name;
    this.age = age;
    this.salary = salary;
    this.email_id = email_id;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return employee_id;
    case 1: return fist_name;
    case 2: return last_name;
    case 3: return age;
    case 4: return salary;
    case 5: return email_id;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: employee_id = value$ != null ? value$.toString() : null; break;
    case 1: fist_name = value$ != null ? value$.toString() : null; break;
    case 2: last_name = value$ != null ? value$.toString() : null; break;
    case 3: age = (java.lang.Integer)value$; break;
    case 4: salary = (java.lang.Float)value$; break;
    case 5: email_id = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'employee_id' field.
   * @return Employee Id
   */
  public java.lang.String getEmployeeId() {
    return employee_id;
  }



  /**
   * Gets the value of the 'fist_name' field.
   * @return First name of the employee
   */
  public java.lang.String getFistName() {
    return fist_name;
  }



  /**
   * Gets the value of the 'last_name' field.
   * @return Last name of the employee
   */
  public java.lang.String getLastName() {
    return last_name;
  }



  /**
   * Gets the value of the 'age' field.
   * @return Age of the employee
   */
  public int getAge() {
    return age;
  }



  /**
   * Gets the value of the 'salary' field.
   * @return Salary
   */
  public float getSalary() {
    return salary;
  }



  /**
   * Gets the value of the 'email_id' field.
   * @return Employee email id
   */
  public java.lang.String getEmailId() {
    return email_id;
  }



  /**
   * Creates a new EmployeeV8 RecordBuilder.
   * @return A new EmployeeV8 RecordBuilder
   */
  public static com.ketul.avro.schema.EmployeeV8.Builder newBuilder() {
    return new com.ketul.avro.schema.EmployeeV8.Builder();
  }

  /**
   * Creates a new EmployeeV8 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new EmployeeV8 RecordBuilder
   */
  public static com.ketul.avro.schema.EmployeeV8.Builder newBuilder(com.ketul.avro.schema.EmployeeV8.Builder other) {
    if (other == null) {
      return new com.ketul.avro.schema.EmployeeV8.Builder();
    } else {
      return new com.ketul.avro.schema.EmployeeV8.Builder(other);
    }
  }

  /**
   * Creates a new EmployeeV8 RecordBuilder by copying an existing EmployeeV8 instance.
   * @param other The existing instance to copy.
   * @return A new EmployeeV8 RecordBuilder
   */
  public static com.ketul.avro.schema.EmployeeV8.Builder newBuilder(com.ketul.avro.schema.EmployeeV8 other) {
    if (other == null) {
      return new com.ketul.avro.schema.EmployeeV8.Builder();
    } else {
      return new com.ketul.avro.schema.EmployeeV8.Builder(other);
    }
  }

  /**
   * RecordBuilder for EmployeeV8 instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<EmployeeV8>
    implements org.apache.avro.data.RecordBuilder<EmployeeV8> {

    /** Employee Id */
    private java.lang.String employee_id;
    /** First name of the employee */
    private java.lang.String fist_name;
    /** Last name of the employee */
    private java.lang.String last_name;
    /** Age of the employee */
    private int age;
    /** Salary */
    private float salary;
    /** Employee email id */
    private java.lang.String email_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.ketul.avro.schema.EmployeeV8.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.employee_id)) {
        this.employee_id = data().deepCopy(fields()[0].schema(), other.employee_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.fist_name)) {
        this.fist_name = data().deepCopy(fields()[1].schema(), other.fist_name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.salary)) {
        this.salary = data().deepCopy(fields()[4].schema(), other.salary);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.email_id)) {
        this.email_id = data().deepCopy(fields()[5].schema(), other.email_id);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing EmployeeV8 instance
     * @param other The existing instance to copy.
     */
    private Builder(com.ketul.avro.schema.EmployeeV8 other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.employee_id)) {
        this.employee_id = data().deepCopy(fields()[0].schema(), other.employee_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.fist_name)) {
        this.fist_name = data().deepCopy(fields()[1].schema(), other.fist_name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.last_name)) {
        this.last_name = data().deepCopy(fields()[2].schema(), other.last_name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.age)) {
        this.age = data().deepCopy(fields()[3].schema(), other.age);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.salary)) {
        this.salary = data().deepCopy(fields()[4].schema(), other.salary);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.email_id)) {
        this.email_id = data().deepCopy(fields()[5].schema(), other.email_id);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'employee_id' field.
      * Employee Id
      * @return The value.
      */
    public java.lang.String getEmployeeId() {
      return employee_id;
    }


    /**
      * Sets the value of the 'employee_id' field.
      * Employee Id
      * @param value The value of 'employee_id'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setEmployeeId(java.lang.String value) {
      validate(fields()[0], value);
      this.employee_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'employee_id' field has been set.
      * Employee Id
      * @return True if the 'employee_id' field has been set, false otherwise.
      */
    public boolean hasEmployeeId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'employee_id' field.
      * Employee Id
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearEmployeeId() {
      employee_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'fist_name' field.
      * First name of the employee
      * @return The value.
      */
    public java.lang.String getFistName() {
      return fist_name;
    }


    /**
      * Sets the value of the 'fist_name' field.
      * First name of the employee
      * @param value The value of 'fist_name'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setFistName(java.lang.String value) {
      validate(fields()[1], value);
      this.fist_name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'fist_name' field has been set.
      * First name of the employee
      * @return True if the 'fist_name' field has been set, false otherwise.
      */
    public boolean hasFistName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'fist_name' field.
      * First name of the employee
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearFistName() {
      fist_name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'last_name' field.
      * Last name of the employee
      * @return The value.
      */
    public java.lang.String getLastName() {
      return last_name;
    }


    /**
      * Sets the value of the 'last_name' field.
      * Last name of the employee
      * @param value The value of 'last_name'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setLastName(java.lang.String value) {
      validate(fields()[2], value);
      this.last_name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'last_name' field has been set.
      * Last name of the employee
      * @return True if the 'last_name' field has been set, false otherwise.
      */
    public boolean hasLastName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'last_name' field.
      * Last name of the employee
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearLastName() {
      last_name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * Age of the employee
      * @return The value.
      */
    public int getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * Age of the employee
      * @param value The value of 'age'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setAge(int value) {
      validate(fields()[3], value);
      this.age = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * Age of the employee
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'age' field.
      * Age of the employee
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearAge() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'salary' field.
      * Salary
      * @return The value.
      */
    public float getSalary() {
      return salary;
    }


    /**
      * Sets the value of the 'salary' field.
      * Salary
      * @param value The value of 'salary'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setSalary(float value) {
      validate(fields()[4], value);
      this.salary = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'salary' field has been set.
      * Salary
      * @return True if the 'salary' field has been set, false otherwise.
      */
    public boolean hasSalary() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'salary' field.
      * Salary
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearSalary() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'email_id' field.
      * Employee email id
      * @return The value.
      */
    public java.lang.String getEmailId() {
      return email_id;
    }


    /**
      * Sets the value of the 'email_id' field.
      * Employee email id
      * @param value The value of 'email_id'.
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder setEmailId(java.lang.String value) {
      validate(fields()[5], value);
      this.email_id = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'email_id' field has been set.
      * Employee email id
      * @return True if the 'email_id' field has been set, false otherwise.
      */
    public boolean hasEmailId() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'email_id' field.
      * Employee email id
      * @return This builder.
      */
    public com.ketul.avro.schema.EmployeeV8.Builder clearEmailId() {
      email_id = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public EmployeeV8 build() {
      try {
        EmployeeV8 record = new EmployeeV8();
        record.employee_id = fieldSetFlags()[0] ? this.employee_id : (java.lang.String) defaultValue(fields()[0]);
        record.fist_name = fieldSetFlags()[1] ? this.fist_name : (java.lang.String) defaultValue(fields()[1]);
        record.last_name = fieldSetFlags()[2] ? this.last_name : (java.lang.String) defaultValue(fields()[2]);
        record.age = fieldSetFlags()[3] ? this.age : (java.lang.Integer) defaultValue(fields()[3]);
        record.salary = fieldSetFlags()[4] ? this.salary : (java.lang.Float) defaultValue(fields()[4]);
        record.email_id = fieldSetFlags()[5] ? this.email_id : (java.lang.String) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<EmployeeV8>
    WRITER$ = (org.apache.avro.io.DatumWriter<EmployeeV8>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<EmployeeV8>
    READER$ = (org.apache.avro.io.DatumReader<EmployeeV8>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.employee_id);

    out.writeString(this.fist_name);

    out.writeString(this.last_name);

    out.writeInt(this.age);

    out.writeFloat(this.salary);

    out.writeString(this.email_id);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.employee_id = in.readString();

      this.fist_name = in.readString();

      this.last_name = in.readString();

      this.age = in.readInt();

      this.salary = in.readFloat();

      this.email_id = in.readString();

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.employee_id = in.readString();
          break;

        case 1:
          this.fist_name = in.readString();
          break;

        case 2:
          this.last_name = in.readString();
          break;

        case 3:
          this.age = in.readInt();
          break;

        case 4:
          this.salary = in.readFloat();
          break;

        case 5:
          this.email_id = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










