DROP TABLE IF EXISTS user_api_limit;
DROP TABLE IF EXISTS api_info;

CREATE TABLE api_info(
  id int NOT NULL PRIMARY KEY,
  url VARCHAR(1024) NOT NULL,
  ratelimit INT not null,
  accuracy VARCHAR(8) default 'LOW'
);

CREATE TABLE user_api_limit (
  user_id VARCHAR(64) NOT NULL,
  api_id int NOT NULL,
  ratelimit INT not null,
  PRIMARY KEY (user_id, api_id),
  Foreign key (api_id) references api_info(id)
);

INSERT INTO api_info (id, url, ratelimit, accuracy) VALUES
  (1, '/api/v1/developers', 10, 'LOW'),
  (2, '/api/v1/organizations', 30, 'HIGH'),
  (3, '/api/v1/books/available', 5, 'HIGh');

INSERT INTO user_api_limit (user_id, api_id, ratelimit) VALUES
  ('user1', 1, 100),
  ('user1', 2, 200),
  ('user2', 1, 50),
  ('user2', 2, 300);