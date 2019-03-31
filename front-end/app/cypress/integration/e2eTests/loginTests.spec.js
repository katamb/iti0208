describe('Test Login Component', function() {
    it('Tests Login', function() {
        cy.visit('http://localhost:8080')
        cy.contains("Login").click()
        cy.get('[name="username"]').type("kalle")
        cy.get('[name="password"]').type("malle")
        cy.get('[name="username"]').type("123")
        cy.get('[name="password"]').type("123")
        cy.get('[name="login"]').click()
        cy.contains("Login").click()
        cy.get('[name="username"]').clear().type("Mango")
        cy.get('[name="password"]').clear().type("Password")
        cy.get('[name="login"]').click()

    })
})