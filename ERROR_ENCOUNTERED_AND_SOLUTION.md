1.org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'productServiceApplication': Unsatisfied dependency expressed through constructor parameter 1: Error creating bean with name 'productRepository' defined in com.productservice.productservice.repository.ProductRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.List findAllByTitle(java.lang.String); Reason: Failed to create query for method public abstract java.util.List findAllByTitle(java.lang.String); No property 'allByTitle' found for type 'Product'
    
        solution - >Reason: Failed to create query for method public abstract java.util.List com.productservice.productservice.repository.ProductRepository.findByAllByTitle(java.lang.String); 
                    RENAMED THE findByAllByTitle RESOLVED THE ISSUE 