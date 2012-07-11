dataSource
{ pooled = true }
hibernate
{
	cache.use_query_cache = true
	cache.use_second_level_cache = true
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments
{
	development
	{
		dataSource
		{
			dbCreate = "create-drop" // create-drop, update
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect
			url = "jdbc:postgresql://localhost:5432/tedza"
			username = "tedza"
			password = "123456"
			properties
			{
				maxActive = -1
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=true
				validationQuery="SELECT 1"
			}
		}
	}
	test
	{
		dataSource
		{
			dbCreate = "update";
			driverClassName = "org.h2.Driver";
			url = "jdbc:h2:mem:storeTestDb;MVCC=TRUE";
		}
	}
	production
	{
		dataSource
		{
			dbCreate = "update"
			driverClassName = "org.postgresql.Driver"
			dialect = org.hibernate.dialect.PostgreSQLDialect

			uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

			url = "jdbc:postgresql://"+uri.host+uri.path
			username = uri.userInfo.split(":")[0]
			password = uri.userInfo.split(":")[1]
			properties
			{
				maxActive = -1
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=true
				validationQuery="SELECT 1"
			}
		}
	}
}