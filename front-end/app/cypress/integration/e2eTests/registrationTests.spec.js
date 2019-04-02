describe('Test Registration Component', function() {
    it('Visits the App', function() {
        cy.visit('http://localhost:8080')
        cy.contains("Register").click()
        cy.url().should('include', '/registration')
        cy.get("#username").should("have.value", "")
            .type("Mango").should("have.value", "Mango")
        cy.get("#firstname").should("have.value", "")
        cy.contains("Submit").click()
        cy.get("#firstname").type("Malle")
        cy.get("#lastname").type("Maasikas")
        cy.get("#password").type("Password")
        cy.get("#matchingPassword").type("password")
        cy.get("#email").type("mangomaasikas@gmail.com")
        cy.contains("Submit").click()
        cy.get("#matchingPassword").clear()
        cy.get("#matchingPassword").type("Password")
        cy.contains("Submit").click()
        cy.get("#username").clear().type("Moosivaras")
        cy.contains("Submit").click()
        cy.contains("Submit").click()





    })
})


