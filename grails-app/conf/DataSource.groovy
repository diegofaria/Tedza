dataSource {
    dialect = net.sf.hibernate.dialect.PostgreSQLDialect
    driverClassName = "org.postgresql.Driver"
    pooled = true
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            url = "jdbc:postgresql://localhost:5432/tedza"
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            driverClassName = "org.postgresql.Driver"
            username = "tedza"
            password = "123456"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
    }
    production {
        dataSource {
            dataSource {
                dbCreate = "create-drop"
                driverClassName = "org.postgresql.Driver"
                dialect = org.hibernate.dialect.PostgreSQLDialect
            
                uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

                url = "jdbc:postgresql://"+uri.host+uri.path
                username = uri.userInfo.split(":")[0]
                password = uri.userInfo.split(":")[1]
            }
        }
    }
}
