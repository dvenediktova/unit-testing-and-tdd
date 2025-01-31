package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    @Test @Disabled("temporary disabled")
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = 1;
        final String clientName = "dummy client name";
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //endregion
    }

    @Test
    public void shouldNotCreateWhenIdIsNegative() {
        final int negativeId = -1;
        final String dummyName = "name";
        final String exceptionMessage = "id is negative";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(negativeId, dummyName)
        );

        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        final int dummyId = 1;
        final String emptyName = "";
        final String exceptionMessage = "name is empty";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, emptyName)
        );

        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldNotCreateWhenNameIsNull() {
        final int dummyId = 1;
        final String nullName = null;
        final String exceptionMessage = "name is empty";

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Client(dummyId, nullName)
        );

        assertEquals(exceptionMessage, thrown.getMessage());
    }

    @Test
    public void shouldCreateWhenParamsAreValid() {
        final int dummyId = 1;
        final String dummyName = "name";

        Client sut = new Client(dummyId, dummyName);

        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", dummyId)
                .hasFieldOrPropertyWithValue("name", dummyName);
    }

    @Test
    public void shouldNotAddAccountWhenAccountIsNull() {
        final int dummyId = 1;
        final String dummyName = "name";

        Client sut = new Client(dummyId, dummyName);

        Exception thrown = assertThrows(
                IllegalArgumentException.class,
                () -> sut.addAccount(null)
        );

        assertEquals("account is null", thrown.getMessage());
    }
}
