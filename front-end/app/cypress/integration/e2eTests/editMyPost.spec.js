describe('Edit My Post', function() {
    it('Tests Editing Post I Posted', function() {
        cy.visit('http://localhost:8080')
        cy.contains("Login").click()
        cy.get('[name="username"]').type("user_test")
        cy.get('[name="password"]').type("user_test")
        cy.get('[name="login"]').click()
        cy.contains("My Activities").click()
        cy.url().should('include', '/userActivities')
        cy.contains("Post")
        cy.get("#Post").click()
        cy.get("#Post2").clear().type("123")
        cy.get("#Post3").clear().type("Uus Description")
        cy.get("#Post4").click()
        cy.contains("123")
        cy.contains("Uus Description")
    })
});
