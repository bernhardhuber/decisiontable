/*
 * Copyright 2022 berni3.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.huberb.decisiontable.dataframe;

import de.unknownreality.dataframe.DataFrame;
import de.unknownreality.dataframe.DataRow;
import de.unknownreality.dataframe.column.BasicColumn;
import de.unknownreality.dataframe.column.DoubleColumn;
import de.unknownreality.dataframe.column.IntegerColumn;
import de.unknownreality.dataframe.common.Row;
import de.unknownreality.dataframe.common.parser.Parser;
import de.unknownreality.dataframe.filter.FilterPredicate;
import de.unknownreality.dataframe.sort.SortColumn.Direction;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.huberb.decisiontable.Person;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.BigDecimalColumn;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.BigIntegerColumn;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.CalendarValueHolder;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.CalendarValueHolderColumn;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.LocalDateValueHolder;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameColumns.LocalDateValueHolderColumn;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameFactories.PersonDataFrameBigDecimalFactory;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameFactories.PersonDataFrameBigIntegerFactory;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameFactories.PersonDataFrameCalendarFactory;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameFactories.PersonDataFrameFactory;
import org.huberb.decisiontable.dataframe.DataFrameTest.DataFrameFactories.PersonDataFrameLocalDateFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author berni3
 */
public class DataFrameTest {

    @Test
    public void testCreateWithPersonDataFrameFactory() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        assertEquals("male", dataFrame.getRow(0).getString("gender"));
        assertEquals(15, dataFrame.getRow(0).getInteger("age"));
        assertEquals(50, dataFrame.getRow(0).getInteger("weight"));
        {
            final DataFrame df1 = dataFrame
                    .select(FilterPredicate.ge("age", 20)
                            .and(FilterPredicate.le("age", 60)))
                    .sort("age", Direction.Ascending);
            df1.forEach((DataRow dataRow) -> {
                int newAge = dataRow.getInteger("age") + 20;
                dataRow.set("age", newAge);
            });

            System.out.println(String.format("dataFrame:%n%s", toCsv(dataFrame)));
            System.out.println(String.format("df1 select age >=20 and age <= 60:%n%s", toCsv(df1)));
        }
    }

    @Test
    public void testCreateWithPersonDataFrameBigIntegerFactory() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameBigIntegerFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        assertEquals("male", dataFrame.getRow(0).getString("gender"));
        assertEquals(15, dataFrame.getRow(0).get("age", BigInteger.class).intValue());
        assertEquals(50, dataFrame.getRow(0).get("weight", BigInteger.class).intValue());
    }

    @Test
    public void testCreateWithPersonDataFrameBigDecimalFactory() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameBigDecimalFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        assertEquals("male", dataFrame.getRow(0).getString("gender"));
        assertEquals(15, dataFrame.getRow(0).get("age", BigDecimal.class).intValue());
        assertEquals(50, dataFrame.getRow(0).get("weight", BigDecimal.class).intValue());
    }

    @Test
    public void testCreateWithPersonDataFrameCalendarFactory() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameCalendarFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        assertEquals("male", dataFrame.getRow(0).getString("gender"));
        assertEquals(15, dataFrame.getRow(0).get("age", Number.class).intValue());
        assertEquals(50, dataFrame.getRow(0).get("weight", Number.class).intValue());
        {
            final DataFrame df1 = dataFrame.sort("birthday").head();
            System.out.println(String.format("testCreateWithPersonDataFrameCalendarFactory: sort by birthday:%n%s", toCsv(df1)));
            assertEquals("Eva", df1.getRow(0).getString("name"));
            assertEquals("female", dataFrame.getRow(0).getString("gender"));
            assertEquals(73, dataFrame.getRow(0).getInteger("age"));
        }
    }

    @Test
    public void testCreateWithPersonDataFrameLocalDateFactory() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameLocalDateFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        assertEquals("male", dataFrame.getRow(0).getString("gender"));
        assertEquals(15, dataFrame.getRow(0).get("age", Number.class).intValue());
        assertEquals(50, dataFrame.getRow(0).get("weight", Number.class).intValue());
        {
            final DataFrame df1 = dataFrame.sort("birthday").head();
            System.out.println(String.format("testCreateWithPersonDataFrameLocalDateFactory: sort by birthday:%n%s", toCsv(df1)));
            assertEquals("Eva", df1.getRow(0).getString("name"));
            assertEquals("female", dataFrame.getRow(0).getString("gender"));
            assertEquals(73, dataFrame.getRow(0).getInteger("age"));
        }
    }

    @Test
    public void testCreateAndSortByName() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        {
            final DataFrame df1 = dataFrame.sort("name").head();
            System.out.println(String.format("sort by name:%n%s", toCsv(df1)));
            assertEquals("Daniela", df1.getRow(0).getString("name"));
            assertEquals("female", dataFrame.getRow(0).getString("gender"));
            assertEquals(38, dataFrame.getRow(0).getInteger("age"));
        }
    }

    @Test
    public void testCreateAndSortByAge() throws IOException {
        final List<Person> tenPersons = Person.tenPersons();
        final DataFrame dataFrame = new PersonDataFrameFactory()
                .withHeaders()
                .withRows(tenPersons)
                .build();

        assertEquals("Peter", dataFrame.getRow(0).getString("name"));
        {
            final DataFrame df2 = dataFrame.sort("age");
            System.out.println(String.format("sort by age:%n%s", toCsv(df2)));
            assertEquals("Peter", dataFrame.getRow(0).getString("name"));
            assertEquals("male", dataFrame.getRow(0).getString("gender"));
            assertEquals(15, dataFrame.getRow(0).getInteger("age"));
        }
    }

    String toCsv(DataFrame df) throws IOException {
        final String result;
        try (StringWriter sw = new StringWriter()) {
            df.writeCSV(sw, ',', true);
            sw.flush();
            result = sw.toString();
        }
        return result;
    }

    static class DataFrameColumns {

        public static class BigIntegerColumn extends BasicColumn<BigInteger, BigIntegerColumn> {

            private final Parser<BigInteger> parser = new Parser<BigInteger>() {
                @Override
                public BigInteger parse(String s) throws ParseException {
                    BigInteger bi = new BigInteger(s);
                    return bi;
                }

            };

            public BigIntegerColumn() {
                super();
            }

            public BigIntegerColumn(String name) {
                super(name);
            }

            public BigIntegerColumn(String name, BigInteger[] values) {
                super(name, values);
            }

            public BigIntegerColumn(String name, BigInteger[] values, int size) {
                super(name, values, size);
            }

            @Override
            protected BigIntegerColumn getThis() {
                return this;
            }

            @Override
            public Parser<BigInteger> getParser() {
                return parser;
            }

            @Override
            public Class<BigInteger> getType() {
                return BigInteger.class;
            }

            @Override
            public BigIntegerColumn copy() {
                final BigInteger[] copyValues = new BigInteger[values.length];
                toArray(copyValues);
                return new BigIntegerColumn(getName(), copyValues, size());
            }

            @Override
            public BigIntegerColumn copyEmpty() {
                return new BigIntegerColumn(getName());
            }

            @Override
            public <H> BigInteger getValueFromRow(Row<?, H> row, H headerName) {
                return row.get(headerName, BigInteger.class);
            }

            @Override
            public BigInteger getValueFromRow(Row<?, ?> row, int headerIndex) {
                return row.get(headerIndex, BigInteger.class);
            }

        }

        public static class BigDecimalColumn extends BasicColumn<BigDecimal, BigDecimalColumn> {

            private final Parser<BigDecimal> parser = new Parser<BigDecimal>() {
                @Override
                public BigDecimal parse(String s) throws ParseException {
                    BigDecimal bi = new BigDecimal(s);
                    return bi;
                }

            };

            public BigDecimalColumn() {
                super();
            }

            public BigDecimalColumn(String name) {
                super(name);
            }

            public BigDecimalColumn(String name, BigDecimal[] values) {
                super(name, values);
            }

            public BigDecimalColumn(String name, BigDecimal[] values, int size) {
                super(name, values, size);
            }

            @Override
            protected BigDecimalColumn getThis() {
                return this;
            }

            @Override
            public Parser<BigDecimal> getParser() {
                return parser;
            }

            @Override
            public Class<BigDecimal> getType() {
                return BigDecimal.class;
            }

            @Override
            public BigDecimalColumn copy() {
                final BigDecimal[] copyValues = new BigDecimal[values.length];
                toArray(copyValues);
                return new BigDecimalColumn(getName(), copyValues, size());
            }

            @Override
            public BigDecimalColumn copyEmpty() {
                return new BigDecimalColumn(getName());
            }

            @Override
            public <H> BigDecimal getValueFromRow(Row<?, H> row, H headerName) {
                return row.get(headerName, BigDecimal.class);
            }

            @Override
            public BigDecimal getValueFromRow(Row<?, ?> row, int headerIndex) {
                return row.get(headerIndex, BigDecimal.class);
            }

        }

        static class ValueHolder<T> {

            final T t;

            public ValueHolder(T t) {
                this.t = t;
            }

            T get() {
                return this.t;
            }

            @Override
            public String toString() {
                return String.valueOf(this.t);
            }
        }

        static class CalendarValueHolder extends ValueHolder<Calendar> implements Comparable<CalendarValueHolder> {

            final static String DEFAULT_SDF_PATTERN = "yyyy-MM-dd HH:mm:ss";
            final String sdfPattern;

            public CalendarValueHolder(Calendar t) {
                this(t, DEFAULT_SDF_PATTERN);
            }

            public CalendarValueHolder(Calendar t, String sdfPattern) {
                super(t);
                this.sdfPattern = sdfPattern;
            }

            @Override
            public String toString() {
                final SimpleDateFormat sdf = new SimpleDateFormat(sdfPattern);
                final String formatted;
                if (this.t != null) {
                    formatted = sdf.format(t.getTime());
                } else {
                    formatted = "";
                }
                return formatted;
            }

            public String getSdfPattern() {
                return sdfPattern;
            }

            @Override
            public int compareTo(CalendarValueHolder o) {
                final Function<CalendarValueHolder, Long> fcl = (c) -> Optional.ofNullable(c)
                        .map((v) -> v.t)
                        .map((v) -> v.getTime().getTime())
                        .orElse(Long.MIN_VALUE);
                final long vt = fcl.apply(this);
                final long ot = fcl.apply(o);
                final int diff = Long.valueOf(vt).compareTo(ot);
                return diff;
            }

        }

        public static class CalendarValueHolderColumn extends BasicColumn<CalendarValueHolder, CalendarValueHolderColumn> {

            private final Parser<CalendarValueHolder> parser = new Parser<CalendarValueHolder>() {
                @Override
                public CalendarValueHolder parse(String s) throws ParseException {
                    final String sdfPattern = "yyyy-MM-dd HH:mm:ss";
                    final SimpleDateFormat sdf = new SimpleDateFormat(sdfPattern);
                    final Date d = sdf.parse(s);

                    final Calendar cal = new Calendar.Builder().setInstant(d).build();
                    CalendarValueHolder cvh = new CalendarValueHolder(cal);
                    return cvh;
                }

            };

            public CalendarValueHolderColumn() {
                super();
            }

            public CalendarValueHolderColumn(String name) {
                super(name);
            }

            public CalendarValueHolderColumn(String name, CalendarValueHolder[] values) {
                super(name, values);
            }

            public CalendarValueHolderColumn(String name, CalendarValueHolder[] values, int size) {
                super(name, values, size);
            }

            @Override
            protected CalendarValueHolderColumn getThis() {
                return this;
            }

            @Override
            public Parser<CalendarValueHolder> getParser() {
                return parser;
            }

            @Override
            public Class<CalendarValueHolder> getType() {
                return CalendarValueHolder.class;
            }

            @Override
            public CalendarValueHolderColumn copy() {
                final CalendarValueHolder[] copyValues = new CalendarValueHolder[values.length];
                toArray(copyValues);
                return new CalendarValueHolderColumn(getName(), copyValues, size());
            }

            @Override
            public CalendarValueHolderColumn copyEmpty() {
                return new CalendarValueHolderColumn(getName());
            }

            @Override
            public <H> CalendarValueHolder getValueFromRow(Row<?, H> row, H headerName) {
                return row.get(headerName, CalendarValueHolder.class);
            }

            @Override
            public CalendarValueHolder getValueFromRow(Row<?, ?> row, int headerIndex) {
                return row.get(headerIndex, CalendarValueHolder.class);
            }

        }

        static class LocalDateValueHolder extends ValueHolder<LocalDate> implements Comparable<LocalDateValueHolder> {

            final DateTimeFormatter dateTimeFormatter;

            public LocalDateValueHolder(LocalDate t) {
                super(t);
                this.dateTimeFormatter = DateTimeFormatter.ISO_DATE;
            }

            @Override
            public int compareTo(LocalDateValueHolder o) {

                final Function<LocalDateValueHolder, Long> fldl = (c) -> Optional.ofNullable(c)
                        .map((v) -> v.t)
                        .map((LocalDate v) -> v.toEpochDay())
                        .orElse(Long.MIN_VALUE);
                final long vt = fldl.apply(this);
                final long ot = fldl.apply(o);
                final int diff = Long.valueOf(vt).compareTo(ot);
                return diff;
            }

            @Override
            public String toString() {
                return this.t.format(this.dateTimeFormatter);
            }
        }

        public static class LocalDateValueHolderColumn extends BasicColumn<LocalDateValueHolder, LocalDateValueHolderColumn> {

            private final Parser<LocalDateValueHolder> parser = new Parser<LocalDateValueHolder>() {
                @Override
                public LocalDateValueHolder parse(String s) throws ParseException {

                    LocalDate localDate = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
                    return new LocalDateValueHolder(localDate);
                }

            };

            public LocalDateValueHolderColumn() {
                super();
            }

            public LocalDateValueHolderColumn(String name) {
                super(name);
            }

            public LocalDateValueHolderColumn(String name, LocalDateValueHolder[] values) {
                super(name, values);
            }

            public LocalDateValueHolderColumn(String name, LocalDateValueHolder[] values, int size) {
                super(name, values, size);
            }

            @Override
            protected LocalDateValueHolderColumn getThis() {
                return this;
            }

            @Override
            public Parser<LocalDateValueHolder> getParser() {
                return parser;
            }

            @Override
            public Class<LocalDateValueHolder> getType() {
                return LocalDateValueHolder.class;
            }

            @Override
            public LocalDateValueHolderColumn copy() {
                final LocalDateValueHolder[] copyValues = new LocalDateValueHolder[values.length];
                toArray(copyValues);
                return new LocalDateValueHolderColumn(getName(), copyValues, size());
            }

            @Override
            public LocalDateValueHolderColumn copyEmpty() {
                return new LocalDateValueHolderColumn(getName());
            }

            @Override
            public <H> LocalDateValueHolder getValueFromRow(Row<?, H> row, H headerName) {
                return row.get(headerName, LocalDateValueHolder.class);
            }

            @Override
            public LocalDateValueHolder getValueFromRow(Row<?, ?> row, int headerIndex) {
                return row.get(headerIndex, LocalDateValueHolder.class);
            }

        }
    }

    static class DataFrameFactories {

        static interface BeanDataFrameFactory<T, B> {

            B withHeaders();

            B withRows(List<T> l);

            DataFrame build();
        }

        static class PersonDataFrameFactory implements BeanDataFrameFactory<Person, PersonDataFrameFactory> {

            DataFrame dataFrame;

            @Override
            public PersonDataFrameFactory withHeaders() {
                this.dataFrame = DataFrame.builder()
                        .addStringColumn("name")
                        .addStringColumn("gender")
                        .addIntegerColumn("age")
                        .addIntegerColumn("weight")
                        .build();

                return this;
            }

            @Override
            public PersonDataFrameFactory withRows(List<Person> tenPersons) {

                for (int i = 0; i < tenPersons.size(); i += 1) {
                    final Person p = tenPersons.get(i);
                    //---
                    final DataRow dataRow = new DataRow(dataFrame, i);
                    dataRow.set("name", p.getName());
                    dataRow.set("gender", p.getGender().name());
                    dataRow.set("age", p.getAge());
                    dataRow.set("weight", p.getWeight());

                    dataFrame.append(dataRow);
                }
                return this;
            }

            @Override
            public DataFrame build() {
                return this.dataFrame;
            }
        }

        static class PersonDataFrameBigIntegerFactory implements BeanDataFrameFactory<Person, PersonDataFrameBigIntegerFactory> {

            DataFrame dataFrame;

            @Override
            public PersonDataFrameBigIntegerFactory withHeaders() {
                this.dataFrame = DataFrame.builder()
                        .addStringColumn("name")
                        .addStringColumn("gender")
                        .addColumn("age", new BigIntegerColumn())
                        .addColumn("weight", new BigIntegerColumn())
                        .build();

                return this;
            }

            @Override
            public PersonDataFrameBigIntegerFactory withRows(List<Person> tenPersons) {

                for (int i = 0; i < tenPersons.size(); i += 1) {
                    final Person p = tenPersons.get(i);
                    //---
                    final DataRow dataRow = new DataRow(dataFrame, i);
                    dataRow.set("name", p.getName());
                    dataRow.set("gender", p.getGender().name());
                    dataRow.set("age", BigInteger.valueOf(p.getAge()));
                    dataRow.set("weight", BigInteger.valueOf(p.getWeight()));

                    dataFrame.append(dataRow);
                }
                return this;
            }

            @Override
            public DataFrame build() {
                return this.dataFrame;
            }
        }

        static class PersonDataFrameBigDecimalFactory implements BeanDataFrameFactory<Person, PersonDataFrameBigDecimalFactory> {

            DataFrame dataFrame;

            @Override
            public PersonDataFrameBigDecimalFactory withHeaders() {
                this.dataFrame = DataFrame.builder()
                        .addStringColumn("name")
                        .addStringColumn("gender")
                        .addColumn("age", new BigDecimalColumn())
                        .addColumn("weight", new BigDecimalColumn())
                        .build();

                return this;
            }

            @Override
            public PersonDataFrameBigDecimalFactory withRows(List<Person> tenPersons) {

                for (int i = 0; i < tenPersons.size(); i += 1) {
                    final Person p = tenPersons.get(i);
                    //---
                    final DataRow dataRow = new DataRow(dataFrame, i);
                    dataRow.set("name", p.getName());
                    dataRow.set("gender", p.getGender().name());
                    dataRow.set("age", BigDecimal.valueOf(p.getAge()));
                    dataRow.set("weight", BigDecimal.valueOf(p.getWeight()));

                    dataFrame.append(dataRow);
                }
                return this;
            }

            @Override
            public DataFrame build() {
                return this.dataFrame;
            }
        }

        static class PersonDataFrameCalendarFactory implements BeanDataFrameFactory<Person, PersonDataFrameCalendarFactory> {

            DataFrame dataFrame;

            @Override
            public PersonDataFrameCalendarFactory withHeaders() {
                this.dataFrame = DataFrame.builder()
                        .addStringColumn("name")
                        .addStringColumn("gender")
                        .addColumn("age", new IntegerColumn())
                        .addColumn("weight", new DoubleColumn())
                        .addColumn("birthday", new CalendarValueHolderColumn())
                        .build();

                return this;
            }

            @Override
            public PersonDataFrameCalendarFactory withRows(List<Person> tenPersons) {

                for (int i = 0; i < tenPersons.size(); i += 1) {
                    final Person p = tenPersons.get(i);
                    //---
                    final DataRow dataRow = new DataRow(dataFrame, i);
                    dataRow.set("name", p.getName());
                    dataRow.set("gender", p.getGender().name());
                    dataRow.set("age", Integer.valueOf(p.getAge()));
                    dataRow.set("weight", Double.valueOf(p.getWeight()));
                    final int age = p.getAge();
                    final Calendar birthday = Calendar.getInstance();
                    birthday.add(Calendar.YEAR, -age);
                    dataRow.set("birthday", new CalendarValueHolder(birthday));

                    dataFrame.append(dataRow);
                }
                return this;
            }

            @Override
            public DataFrame build() {
                return this.dataFrame;
            }
        }

        static class PersonDataFrameLocalDateFactory implements BeanDataFrameFactory<Person, PersonDataFrameLocalDateFactory> {

            DataFrame dataFrame;

            @Override
            public PersonDataFrameLocalDateFactory withHeaders() {
                this.dataFrame = DataFrame.builder()
                        .addStringColumn("name")
                        .addStringColumn("gender")
                        .addColumn("age", new IntegerColumn())
                        .addColumn("weight", new DoubleColumn())
                        .addColumn("birthday", new LocalDateValueHolderColumn())
                        .build();

                return this;
            }

            @Override
            public PersonDataFrameLocalDateFactory withRows(List<Person> tenPersons) {

                for (int i = 0; i < tenPersons.size(); i += 1) {
                    final Person p = tenPersons.get(i);
                    //---
                    final DataRow dataRow = new DataRow(dataFrame, i);
                    dataRow.set("name", p.getName());
                    dataRow.set("gender", p.getGender().name());
                    dataRow.set("age", Integer.valueOf(p.getAge()));
                    dataRow.set("weight", Double.valueOf(p.getWeight()));
                    final int age = p.getAge();
                    final LocalDate birthday = LocalDate.now().minusYears(age);
                    dataRow.set("birthday", new LocalDateValueHolder(birthday));

                    dataFrame.append(dataRow);
                }
                return this;
            }

            @Override
            public DataFrame build() {
                return this.dataFrame;
            }
        }

    }
}
