CREATE TABLE student (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	level INT DEFAULT 0,
	score INT DEFAULT 0
);

CREATE TABLE formula (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	img_url TEXT NOT NULL,
	badge_img_url TEXT NOT NULL
);

CREATE TABLE challenge (
	student_id INT NOT NULL,
	formula_id INT NOT NULL,
	progress_status DECIMAL(4,1) DEFAULT 0.0,
	start_date_time TIMESTAMP NOT NULL,
	finish_date_time TIMESTAMP,

	PRIMARY KEY(student_id, formula_id)
);