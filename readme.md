# Table Row Condition Test

This is an example application, which contains a single non-admin module view, tableRowConditionTest, to demonstrate using a custom component builder to allow data grid to specify individual rows to be enabled or visible based on a condition and responding to a change handler added within the row of a `dataGrid`.

`Invoice` contains a data grid of `InvoiceItems`, which specifies custom change handlers on the `quantity` and `delivery` columns. The `type` column defines an editable condition, so that the type can only be changed when the quantity is > 0.

## Steps Required

The customisations to this project required to make this work:

1. The Invoice [edit.xml](src/main/java/modules/tableRowConditionTest/Invoice/views/edit.xml) specifies properties on the `InvoiceItems` dataGrid which specify the custom behaviour we want to happen in this application:

    ```xml
    <boundColumn binding="quantity">
        <properties>
            <c:property key="rerender">change</c:property>
            <c:property key="process">@this</c:property>
            <c:property key="update">formInvoiceItems</c:property>
        </properties>
    </boundColumn>
    ```
    
3. A custom xhtml page, [invoice.xhtml](src/main/webapp/test/invoice.xhtml) uses the `s:view` element to specify a custom component builder:

    ```xml
    <s:view
        componentBuilderClass="au.com.bizhub.CustomComponentBiulderChain" />
    ```

4. The tableRowConditionTest module [router.xml](src/main/java/modules/tableRowConditionTest/router.xml) defines a route to the custom xhtml page when the Invoice document is being requested. This is what makes Skyve load the custom xhtml page instead of the standard edit page.
5. A [CustomComponentBuilderChain](src/main/java/au/com/bizhub/CustomComponentBiulderChain.java) tells Skyve which order to load the component builders in, which defines the order of rendering, and allows us to provide override behaviour for the `dataGrid` component.
6. A [DataGridComponentBuilder](src/main/java/au/com/bizhub/DataGridComponentBuilder.java) class defines the additional properties we add to the dataGrid widget to allow it to listen for changes and implmenent the custom visbility within a table row.
