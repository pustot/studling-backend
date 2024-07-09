import requests
import mysql.connector

# 第一步，得到常用汉字表
def fetch_hanzi():
    url = "https://raw.githubusercontent.com/nk2028/commonly-used-chinese-characters-and-words/main/char.txt"
    response = requests.get(url)
    response.raise_for_status()
    hanzi_list = response.text.splitlines()
    return {hanzi: [] for hanzi in hanzi_list}

# 第二步，得到每个汉字的各种发音
def fetch_pronunciations(hanzi_map):
    url = "https://raw.githubusercontent.com/rime/rime-cantonese/main/jyut6ping3.chars.dict.yaml"
    response = requests.get(url)
    response.raise_for_status()
    lines = response.text.splitlines()
    parsing = False
    for line in lines:
        line = line.strip()
        if line == "---":
            parsing = False
            continue
        if line == "...":
            parsing = True
            continue
        if not parsing or not line or line.startswith('#'):
            continue
        parts = line.split('\t')
        if len(parts) >= 2:
            hanzi, pronunciation = parts[0], parts[1]
            if hanzi in hanzi_map:
                hanzi_map[hanzi].append(pronunciation)

# 连接数据库
def connect_to_database():
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user="your_username",
            password="your_password",
            database="your_database_name"
        )
        return connection
    except mysql.connector.Error as err:
        print(f"Error: {err}")
        return None

# 建表
def create_table(connection):
    try:
        cursor = connection.cursor()
        create_table_query = """
        CREATE TABLE IF NOT EXISTS zh_yue_can_words (
            word_id INT AUTO_INCREMENT PRIMARY KEY,
            word VARCHAR(255) NOT NULL,
            pronunciation VARCHAR(255),
            meaning VARCHAR(255),
            example_combination VARCHAR(255),
            example_sentence TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            INDEX (word)
        )
        """
        cursor.execute(create_table_query)
        connection.commit()
        cursor.close()
    except mysql.connector.Error as err:
        print(f"Error: {err}")

# 插入刚得到的汉字读音表，若汉字已存在则更新
def insert_or_update_words(connection, hanzi_map):
    try:
        cursor = connection.cursor()
        for hanzi, pronunciations in hanzi_map.items():
            pronunciation_str = ",".join(pronunciations)
            cursor.execute("""
                INSERT INTO zh_yue_can_words (word, pronunciation)
                VALUES (%s, %s)
                ON DUPLICATE KEY UPDATE
                pronunciation = VALUES(pronunciation)
            """, (hanzi, pronunciation_str))
        connection.commit()
        cursor.close()
    except mysql.connector.Error as err:
        print(f"Error: {err}")

def main():
    hanzi_map = fetch_hanzi()
    fetch_pronunciations(hanzi_map)
    connection = connect_to_database()
    if connection:
        create_table(connection)
        insert_or_update_words(connection, hanzi_map)
        connection.close()

if __name__ == "__main__":
    main()
