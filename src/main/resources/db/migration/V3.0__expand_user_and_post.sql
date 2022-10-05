ALTER table user add column created_on datetime after city;
ALTER table user add column modified_on datetime after created_on;
ALTER table post add column created_on datetime after downvote;
ALTER table post add column modified_on datetime after created_on;