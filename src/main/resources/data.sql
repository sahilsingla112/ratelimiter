DROP TABLE IF EXISTS user_api_limit;

CREATE TABLE user_api_limit (
  userid VARCHAR(250) NOT NULL,
  api VARCHAR(1024) NOT NULL,
  ratelimit INT not null,
  PRIMARY KEY (userid, api)
);

INSERT INTO user_api_limit (userid, api, ratelimit) VALUES
  ('user1', '/api/v1/developers', 100),
  ('user1', '/api/v1/organizations', 200),
  ('user2', '/api/v1/developers', 50),
  ('user2', '/api/v1/organizations', 300);