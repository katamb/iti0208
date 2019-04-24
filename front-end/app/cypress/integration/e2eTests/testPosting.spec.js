describe('Test Posting', function() {
    it('Tests Posting When Logged In', function() {
        cy.visit('http://localhost:8080')
        cy.contains("Login").click()
        cy.get('[name="username"]').type("user_test")
        cy.get('[name="password"]').type("user_test")
        cy.get('[name="login"]').click()
        cy.get("#postProblems").click()
        cy.url().should('include', '/addpost')
        cy.get('[name="topic"]').select("Mathematics")
        cy.contains("Submit").click()
        cy.get("#title").type("Post")
        cy.get("#description").type("Description")
        cy.contains("Submit").click()

    })
});