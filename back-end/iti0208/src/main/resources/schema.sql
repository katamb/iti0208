/*CREATE TABLE app_user (
                        id serial primary key,
                        username varchar(255) unique,
                        first_name varchar(255),
                        last_name varchar(255),
                        email varchar(255),
                        password varchar(255)
);*/

/*CREATE TABLE reply (
                      id serial primary key,
                      reply varchar(255),
                      post_id bigint,
                      file_location varchar(255),
                      posted_at timestamp,
                      user_id bigint,
                      posted_by varchar(255),
                      FOREIGN KEY (post_id)
                        REFERENCES post ON DELETE CASCADE,
                      FOREIGN KEY (user_id)
                        REFERENCES app_user ON DELETE CASCADE,
                      FOREIGN KEY (posted_by)
                        REFERENCES app_user (username) ON DELETE CASCADE
);*/

/*CREATE TABLE post (
                    id serial primary key,
                    topic varchar(255),
                    title varchar(255),
                    description varchar(255),
                    reward_description varchar(255),
                    file_location varchar(255),
                    posted_at timestamp,
                    user_id bigint,
                    posted_by varchar(255),
                    FOREIGN KEY (user_id)
                      REFERENCES app_user ON DELETE CASCADE,
                    FOREIGN KEY (posted_by)
                      REFERENCES app_user (username) ON DELETE CASCADE
);*/